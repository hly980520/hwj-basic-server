package com.hwj.basic.server.activity.service.impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.common.activity.dto.ActivityDTO;
import com.hwj.basic.common.activity.service.ActivityWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.activity.converter.ActivityEntityConverter;
import com.hwj.basic.server.activity.entity.ActivityEntity;
import com.hwj.basic.server.activity.manager.ActivityEntityManager;
import com.hwj.basic.server.constant.DubboConst;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@DubboService(provider = DubboConst.PROVIDER)
public class ActivityWriteServiceImpl implements ActivityWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityWriteServiceImpl.class);

    @Resource
    private ActivityEntityManager activityEntityManager;

    @Resource
    private ActivityEntityConverter activityEntityConverter;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public RpcResult<ActivityDTO> create(ActivityDTO activityDTO) {
        if (Objects.isNull(activityDTO)){
            LOGGER.warn("Create Activity Failed:Invaild Paraments");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        String lockKey = "ACTIVITY_LOCK" + activityDTO.getName();
        String requestId = UUID.randomUUID().toString();
        boolean lockAcquired = false;

        try {
            //获取分布式锁，设置过期时间
            redisTemplate.opsForValue().setIfAbsent(lockKey,requestId,10, TimeUnit.SECONDS);

            if (!lockAcquired){
                LOGGER.warn("Create Activity Failed: Concurrent Request For [{}]",activityDTO.getName());
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }

            //再次检查活动名称是否重复
            ActivityEntity checkParams = new ActivityEntity();
            checkParams.setName(activityDTO.getName());
            if (activityEntityManager.selectOne(checkParams) != null){
                LOGGER.warn("Create Activity Failed: Name Exist [{}]",activityDTO.getName());
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }

            //创建活动
            ActivityEntity activityEntity = activityEntityConverter.from(activityDTO);
            boolean insert = activityEntityManager.insert(activityEntity);
            if (!insert){
                LOGGER.warn("Create Activity Failed:Insert Database Error");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            ActivityDTO data = activityEntityConverter.toDTO(activityEntity);
            return RpcResult.success(data);

        }catch (Exception e){
            LOGGER.error("Create Activity Failed: {}", e.getMessage(), e);
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }finally {
            // 4. 释放锁
            if (lockAcquired) {
                String luaScript =
                        "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                                "   return redis.call('del', KEYS[1]) " +
                                "else " +
                                "   return 0 " +
                                "end";
                redisTemplate.execute(
                        new DefaultRedisScript<>(luaScript, Long.class),
                        Collections.singletonList(lockKey),
                        requestId
                );
            }
        }
    }

    @Override
    public RpcResult<ActivityDTO> deletedById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("Activity Deleted Failed: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("Activity Delete Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        boolean success = activityEntityManager.deletedById(id);
        return success ? RpcResult.success() : ErrorCode.DELETE_FAILED.toRpcResult();
    }

    @Override
    public RpcResult<ActivityDTO> update(ActivityDTO activityDTO) {
        if (Objects.isNull(activityDTO)){
            LOGGER.warn("Update Failed: ActivityDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = activityDTO.getId();
        if (id == null || id <= 0){
            LOGGER.warn("Update Failed: Id Is Null or Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        try {
            ActivityEntity checkExist = activityEntityManager.selectById(id);
            if (checkExist == null){
                LOGGER.warn("Update Failded: Activity Not Exist");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }

            ActivityEntity updateEntity = activityEntityConverter.from(activityDTO);
            updateEntity.setId(id);

            boolean success = activityEntityManager.updateById(updateEntity);
            if (!success) {
                LOGGER.error("Update Failed: Database Error [ID={}]", id);
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }

            ActivityEntity latestEntity = activityEntityManager.selectById(id);
            ActivityDTO data = activityEntityConverter.toDTO(latestEntity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("Update Failed: System Error [ID={}], Reason: {}", id, e.getMessage(), e);
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }
}
