package com.hwj.basic.server.activitymember.service.impl;

import com.hwj.basic.common.activity.dto.ActivityDTO;
import com.hwj.basic.common.activitymember.dto.ActivityMemberDTO;
import com.hwj.basic.common.activitymember.service.ActivityMemberWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.activitymember.converter.ActivityMemberEntityConverter;
import com.hwj.basic.server.activitymember.entity.ActivityMemberEntity;
import com.hwj.basic.server.activitymember.manager.ActivityMemberEntityManager;
import com.hwj.basic.server.constant.DubboConst;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class ActivityMemberWriteServiceImpl implements ActivityMemberWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityMemberWriteServiceImpl.class);

    @Resource
    private ActivityMemberEntityManager activityMemberEntityManager;

    @Resource
    private ActivityMemberEntityConverter activityMemberEntityConverter;

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public RpcResult<ActivityMemberDTO> updateStatus(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("ActivityMember updateStatus Failed: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("ActivityMember updateStatus Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        ActivityMemberEntity params = new ActivityMemberEntity();
        params.setId(id);
        params.setStatus(0);
        boolean success = activityMemberEntityManager.updateById(params);
        if (!success){
            LOGGER.warn("ActivityMember updateStatus Failed: Update status Failed");
            return ErrorCode.UPDATE_FAILED.toRpcResult();
        }
        ActivityMemberDTO data = activityMemberEntityConverter.toDTO(params);
        return RpcResult.success(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RpcResult<ActivityMemberDTO> update(ActivityMemberDTO activityMemberDTO) {
        if (Objects.isNull(activityMemberDTO)){
            LOGGER.warn("ActivityMember Update Failed: activityMemberDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = activityMemberDTO.getId();
        if (id == null || id <= 0){
            LOGGER.warn("ActivityMember Update Failed: Id Is Null or Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        try {
            ActivityMemberEntity checkExist = activityMemberEntityManager.selectById(id);
            if (checkExist == null){
                LOGGER.warn("ActivityMember Update Failed: ActivityMember Not Exist");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }

            ActivityMemberEntity updateEntity = activityMemberEntityConverter.from(activityMemberDTO);
            updateEntity.setId(id);

            boolean success = activityMemberEntityManager.updateById(updateEntity);
            if (!success){
                LOGGER.warn("ActivityMember Update Failed: Database Error [ID={}]",id);
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }

            ActivityMemberEntity lastEntity = activityMemberEntityManager.selectById(id);
            ActivityMemberDTO data = activityMemberEntityConverter.toDTO(lastEntity);
            return RpcResult.success(data);

        }catch (Exception e){
            LOGGER.error("ActivityMember Update Failed: System Error [ID={}], Reason: {}", id, e.getMessage(), e);
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

}
