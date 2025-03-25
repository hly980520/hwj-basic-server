package com.hwj.basic.server.activitytask.service.impl;

import com.hwj.basic.common.activitytask.dto.ActivityTaskDTO;
import com.hwj.basic.common.activitytask.service.ActivityTaskWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.activitytask.converter.ActivityTaskConverter;
import com.hwj.basic.server.activitytask.entity.ActivityTaskEntity;
import com.hwj.basic.server.activitytask.manager.ActivityTaskManager;
import com.hwj.basic.server.constant.DubboConst;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class ActivityTaskWriteServiceImpl implements ActivityTaskWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityTaskWriteServiceImpl.class);

    @Resource
    private ActivityTaskManager activityTaskManager;

    @Resource
    private ActivityTaskConverter activityTaskConverter;

    @Override
    public RpcResult<ActivityTaskDTO> create(ActivityTaskDTO activityTaskDTO) {

        if (Objects.isNull(activityTaskDTO)){
            LOGGER.warn("ActivityTask Create Failed: activityTaskDTO Or Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        String name = activityTaskDTO.getName();
        if (StringUtils.isEmpty(name)) {
            LOGGER.warn("ActivityTask Create Failed: Name Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        ActivityTaskEntity params = new ActivityTaskEntity();
        params.setName(name);
        ActivityTaskEntity checkExist = activityTaskManager.selectOne(params);
        if (Objects.nonNull(checkExist)){
            LOGGER.warn("ActivityTask Create Failed: Name Is Exists");
            return ErrorCode.INSERT_FAILED.toRpcResult();
        }

        ActivityTaskEntity entity = activityTaskConverter.from(activityTaskDTO);
        try {
            boolean success = activityTaskManager.insert(entity);
            if (!success){
                LOGGER.warn("ActivityTask Create Failed: Insert Database Failded");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            ActivityTaskDTO data = activityTaskConverter.toDTO(entity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("ActivityTask Create Failed: System Exception Occurred,[{}]", e);
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }

    }

    @Override
    public RpcResult<ActivityTaskDTO> update(ActivityTaskDTO activityTaskDTO) {
        if (Objects.isNull(activityTaskDTO)){
            LOGGER.warn("ActivityTask Update Failed: ActivityTaskDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = activityTaskDTO.getId();
        if (id == null || id <= 0){
            LOGGER.warn("ActivityTask Update Failed: Id Is Null or Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        ActivityTaskEntity checkExist = activityTaskManager.selectById(id);
        if (Objects.isNull(checkExist)){
            LOGGER.warn("ActivityTask Update Failed: ActivityTask Not Found,[id={}]",id);
            return ErrorCode.UPDATE_FAILED.toRpcResult();
        }

        ActivityTaskEntity entity = activityTaskConverter.from(activityTaskDTO);
        entity.setId(id);

        try {
            boolean success = activityTaskManager.updateById(entity);
            if (!success){
                LOGGER.warn("ActivityTask Update Failed: Update Databases Error");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }
            ActivityTaskEntity lastEntity = activityTaskManager.selectOne(entity);
            ActivityTaskDTO data = activityTaskConverter.toDTO(lastEntity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("ActivityTask Update Failed: System Error,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<ActivityTaskDTO> deleted(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("ActivityTask Delete Failed: Id is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("ActivityTask Delete Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        try {
            boolean success = activityTaskManager.deletedById(id);
            return success ? RpcResult.success() : ErrorCode.DELETE_FAILED.toRpcResult();

        }catch (Exception e){
            LOGGER.error("ActivityTask Delete Failed: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

}
