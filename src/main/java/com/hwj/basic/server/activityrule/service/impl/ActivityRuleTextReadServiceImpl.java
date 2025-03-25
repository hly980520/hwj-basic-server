package com.hwj.basic.server.activityrule.service.impl;

import com.hwj.basic.common.activityrule.dto.ActivityRuleTextDTO;
import com.hwj.basic.common.activityrule.service.ActivityRuleTextReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.activityrule.converter.ActivityRuleTextEntityConverter;
import com.hwj.basic.server.activityrule.entity.ActivityRuleTextEntity;
import com.hwj.basic.server.activityrule.manager.ActivityRuleTextManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Objects;

@Repository
public class ActivityRuleTextReadServiceImpl implements ActivityRuleTextReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityRuleTextReadServiceImpl.class);

    @Resource
    private ActivityRuleTextManager activityRuleTextManager;

    @Resource
    private ActivityRuleTextEntityConverter activityRuleTextEntityConverter;

    @Override
    public RpcResult<ActivityRuleTextDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("ActivityRuleText QueryById Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("ActivityRuleText QueryById Failded: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        ActivityRuleTextEntity entity = activityRuleTextManager.selectById(id);
        ActivityRuleTextDTO data = activityRuleTextEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<ActivityRuleTextDTO> queryByActivityId(Long activityId) {
        if (Objects.isNull(activityId)){
            LOGGER.warn("ActivityRuleText QueryByActivityId Failded: activityId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (activityId <= 0){
            LOGGER.warn("ActivityRuleText QueryByActivityId Failded: activityId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        ActivityRuleTextEntity params = new ActivityRuleTextEntity();
        params.setActivityId(activityId);
        ActivityRuleTextEntity entity = activityRuleTextManager.selectOne(params);
        ActivityRuleTextDTO data = activityRuleTextEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<ActivityRuleTextDTO> queryByRuleText(String ruleText) {
        if (StringUtils.isEmpty(ruleText)){
            LOGGER.warn("ActivityRuleText QueryByRuleText Failded: ruleText Is Null Or Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        ActivityRuleTextEntity params = new ActivityRuleTextEntity();
        params.setRuleText(ruleText);
        ActivityRuleTextEntity entity = activityRuleTextManager.selectOne(params);
        ActivityRuleTextDTO data = activityRuleTextEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }
}
