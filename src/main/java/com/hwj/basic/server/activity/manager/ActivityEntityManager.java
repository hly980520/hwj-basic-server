package com.hwj.basic.server.activity.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.activity.entity.ActivityEntity;
import com.hwj.basic.server.activity.mapper.ActivityEntityMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class ActivityEntityManager extends HwjBaseManager<ActivityEntityMapper, ActivityEntity,ActivityEntity> {
    @Override
    protected QueryWrapper<ActivityEntity> buildQueryWrapper(ActivityEntity params) {
        if (Objects.isNull(params)){
            return new QueryWrapper<>();
        }
        QueryWrapper<ActivityEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),ActivityEntity::getId,params.getId())
                .like(Objects.nonNull(params.getName()),ActivityEntity::getName,params.getName())
                .eq(Objects.nonNull(params.getActivityType()),ActivityEntity::getActivityType,params.getActivityType())
                .eq(Objects.nonNull(params.getStatus()),ActivityEntity::getStatus,params.getStatus());
        return wrapper;
    }
}
