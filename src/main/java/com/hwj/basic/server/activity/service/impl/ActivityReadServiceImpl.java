package com.hwj.basic.server.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.common.activity.dto.ActivityDTO;
import com.hwj.basic.common.activity.service.ActivityReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.activity.converter.ActivityEntityConverter;
import com.hwj.basic.server.activity.entity.ActivityEntity;
import com.hwj.basic.server.activity.manager.ActivityEntityManager;
import com.hwj.basic.server.constant.DubboConst;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class ActivityReadServiceImpl implements ActivityReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityReadServiceImpl.class);

    @Resource
    private ActivityEntityManager activityEntityManager;

    @Resource
    private ActivityEntityConverter activityEntityConverter;

    @Override
    public RpcResult<ActivityDTO> queryById(Long id) {
        if (Objects.isNull(id)) {
            LOGGER.warn("Activity QueryById: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        ActivityEntity activityEntity = activityEntityManager.selectById(id);
        ActivityDTO data = activityEntityConverter.toDTO(activityEntity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<ActivityDTO> queryByName(String name) {
        if (Objects.isNull(name)){
            LOGGER.warn("Activity QueryByName: Name Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        ActivityEntity params = new ActivityEntity();
        params.setName(name);
        ActivityEntity activityEntity = activityEntityManager.selectOne(params);
        ActivityDTO data = activityEntityConverter.toDTO(activityEntity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<ActivityDTO> queryByType(Integer type) {
        if (Objects.isNull(type)){
            LOGGER.warn("Activity QueryByType: Type is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        ActivityEntity params = new ActivityEntity();
        params.setActivityType(type);
        ActivityEntity activityEntity = activityEntityManager.selectOne(params);
        ActivityDTO data = activityEntityConverter.toDTO(activityEntity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<ActivityDTO> queryByStatus(Integer status) {
        if (Objects.isNull(status)){
            LOGGER.warn("Activity QueryByStatus Failed: Status Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        ActivityEntity params = new ActivityEntity();
        params.setStatus(status);
        ActivityEntity activityEntity = activityEntityManager.selectOne(params);
        ActivityDTO data = activityEntityConverter.toDTO(activityEntity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<List<ActivityDTO>> queryList(Integer status) {
        if (Objects.isNull(status)){
            LOGGER.warn("Activity QueryList Failed: Status Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        ActivityEntity params = new ActivityEntity();
        params.setStatus(status);
        if (status == 0){
            LOGGER.warn("Activity QueryList Failed: Status is 0");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        List<ActivityEntity> activityEntities = activityEntityManager.selectList(params);
        List<ActivityDTO> datas = activityEntityConverter.toDTOList(activityEntities);
        return RpcResult.success(datas);
    }

    @Override
    public RpcResult<List<ActivityDTO>> queryActivityList(List<Long> ids) {
        if (ids.isEmpty()){
            LOGGER.warn("Activity queryActivityList Failed: ids Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        List<ActivityEntity> activityEntities = activityEntityManager.selectByIds(ids);
        List<ActivityDTO> datas = activityEntityConverter.toDTOList(activityEntities);
        return RpcResult.success(datas);
    }

    @Override
    public RpcResult<DataPage<ActivityDTO>> queryPage(DataPage<ActivityDTO> dataPage, ActivityDTO activityDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(activityDTO)) {
            LOGGER.warn("dataPage Is Null Or Member Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        dataPage = activityEntityManager.selectPage(dataPage,
                activityEntityConverter.from(activityDTO),
                this::toActivityList
        );

        return RpcResult.success(dataPage);
    }

    /**
     * 转activityrList
     * @param list ActivityEntity List
     * @return List<ActivityDTO>
     */
    private List<ActivityDTO> toActivityList(List<ActivityEntity> list) {
        return list.stream()
                .map(data -> activityEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
