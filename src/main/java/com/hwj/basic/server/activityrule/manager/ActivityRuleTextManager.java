package com.hwj.basic.server.activityrule.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.activityrule.entity.ActivityRuleTextEntity;
import com.hwj.basic.server.activityrule.mapper.ActivityRuleTextMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class ActivityRuleTextManager extends HwjBaseManager<ActivityRuleTextMapper, ActivityRuleTextEntity,ActivityRuleTextEntity> {
    @Override
    protected QueryWrapper<ActivityRuleTextEntity> buildQueryWrapper(ActivityRuleTextEntity params) {
        if (Objects.isNull(params)){
            return new QueryWrapper<>();
        }
        QueryWrapper<ActivityRuleTextEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),ActivityRuleTextEntity::getId,params.getId())
                .eq(Objects.nonNull(params.getActivityId()),ActivityRuleTextEntity::getActivityId,params.getActivityId())
                .like(Objects.nonNull(params.getRuleText()),ActivityRuleTextEntity::getRuleText,params.getRuleText());
        return wrapper;
    }
}
