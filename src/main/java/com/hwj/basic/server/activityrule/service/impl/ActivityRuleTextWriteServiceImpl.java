package com.hwj.basic.server.activityrule.service.impl;

import com.hwj.basic.common.activityrule.dto.ActivityRuleTextDTO;
import com.hwj.basic.common.activityrule.service.ActivityRuleTextWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.activityrule.converter.ActivityRuleTextEntityConverter;
import com.hwj.basic.server.activityrule.entity.ActivityRuleTextEntity;
import com.hwj.basic.server.activityrule.manager.ActivityRuleTextManager;
import com.hwj.basic.server.constant.DubboConst;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class ActivityRuleTextWriteServiceImpl implements ActivityRuleTextWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityRuleTextWriteServiceImpl.class);

    @Resource
    private ActivityRuleTextManager activityRuleTextManager;

    @Resource
    private ActivityRuleTextEntityConverter activityRuleTextEntityConverter;

    @Override
    public RpcResult<ActivityRuleTextDTO> create(ActivityRuleTextDTO activityRuleTextDTO) {
        if (Objects.isNull(activityRuleTextDTO)) {
            LOGGER.warn("ActivityRuleText Create Failded: activityRuleTextDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        Long activityId = activityRuleTextDTO.getActivityId();
        if (activityId == null){
            LOGGER.warn("ActivityRuleText Create Failded: ActivityId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        ActivityRuleTextEntity entity = activityRuleTextEntityConverter.from(activityRuleTextDTO);

        try {
            boolean success = activityRuleTextManager.insert(entity);
            if (!success) {
                LOGGER.warn("ActivityRuleText Create Failded: Insert Database Failded");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            ActivityRuleTextDTO data = activityRuleTextEntityConverter.toDTO(entity);
            return RpcResult.success(data);
        } catch (Exception e) {
            LOGGER.error("ActivityRuleText Create Failded: System Exception Error,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<ActivityRuleTextDTO> update(ActivityRuleTextDTO activityRuleTextDTO) {
        if (Objects.isNull(activityRuleTextDTO)) {
            LOGGER.warn("ActivityRuleText Update Failded: activityRuleTextDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        Long id = activityRuleTextDTO.getId();
        if (id == null){
            LOGGER.warn("ActivityRuleText Update Failded: ActivityRuleText Not Exists");
            return ErrorCode.UPDATE_FAILED.toRpcResult();
        }
        ActivityRuleTextEntity params = new ActivityRuleTextEntity();
        params.setId(id);

        try {
            boolean success = activityRuleTextManager.updateById(params);
            if (!success){
                LOGGER.warn("ActivityRuleText Update Failded: Update Database Failded");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }
            ActivityRuleTextDTO data = activityRuleTextEntityConverter.toDTO(params);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("ActivityRuleText Update Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<ActivityRuleTextDTO> delete(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("ActivityRuleText Delete Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        try {
            boolean success = activityRuleTextManager.deletedById(id);
            if (!success){
                LOGGER.warn("ActivityRuleText Delete Failded: Delete Database Failded");
                return ErrorCode.DELETE_FAILED.toRpcResult();
            }
            return RpcResult.success();
        }catch (Exception e){
            LOGGER.error("ActivityRuleText Delete Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }
}
