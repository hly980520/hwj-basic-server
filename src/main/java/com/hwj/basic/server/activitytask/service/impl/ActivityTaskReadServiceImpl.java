package com.hwj.basic.server.activitytask.service.impl;

import com.hwj.basic.common.activitytask.dto.ActivityTaskDTO;
import com.hwj.basic.common.activitytask.service.ActivityTaskReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.activitytask.converter.ActivityTaskConverter;
import com.hwj.basic.server.activitytask.entity.ActivityTaskEntity;
import com.hwj.basic.server.activitytask.manager.ActivityTaskManager;
import com.hwj.basic.server.constant.DubboConst;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class ActivityTaskReadServiceImpl implements ActivityTaskReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTaskReadServiceImpl.class);

    @Resource
    private ActivityTaskManager activityTaskManager;

    @Resource
    private ActivityTaskConverter activityTaskConverter;

    @Override
    public RpcResult<ActivityTaskDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("ActivityTask QueryById Failed: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("ActivityTask QueryById Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        ActivityTaskEntity entity = activityTaskManager.selectById(id);
        ActivityTaskDTO data = activityTaskConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<ActivityTaskDTO> queryByName(String name) {
        if (Objects.isNull(name)){
            LOGGER.warn("ActivityTask QueryByName Failed: Name Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        ActivityTaskEntity params = new ActivityTaskEntity();
        params.setName(name);
        ActivityTaskEntity entity = activityTaskManager.selectOne(params);
        ActivityTaskDTO data = activityTaskConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<ActivityTaskDTO> queryByActivityId(Long activityId) {
        if (Objects.isNull(activityId)){
            LOGGER.warn("ActivityTask QueryByActivityId Failed: ActivityId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (activityId <= 0){
            LOGGER.warn("ActivityTask QueryByActivityId Failed: ActivityId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        ActivityTaskEntity params = new ActivityTaskEntity();
        params.setActivityId(activityId);
        ActivityTaskEntity entity = activityTaskManager.selectOne(params);
        ActivityTaskDTO data = activityTaskConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<ActivityTaskDTO> queryByStatus(Integer status) {
        if (Objects.isNull(status)){
            LOGGER.warn("ActivityTask QueryByStatus Failed: Status Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (status < 0 || status > 2){
            LOGGER.warn("ActivityTask QueryByStatus Failed: Status Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        ActivityTaskEntity params = new ActivityTaskEntity();
        params.setStatus(status);
        ActivityTaskEntity entity = activityTaskManager.selectOne(params);
        ActivityTaskDTO data = activityTaskConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<ActivityTaskDTO>> queryPage(DataPage<ActivityTaskDTO> dataPage, ActivityTaskDTO activityTaskDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(activityTaskDTO)){
            LOGGER.warn("dataPage Is Null Or Member Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        dataPage = activityTaskManager.selectPage(dataPage,
                activityTaskConverter.from(activityTaskDTO),
                this::toActivityTaskList);

        return RpcResult.success(dataPage);
    }

    private List<ActivityTaskDTO> toActivityTaskList(List<ActivityTaskEntity> list){
        return list.stream()
                .map(data -> activityTaskConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
