package com.hwj.basic.server.activitytask.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.activitytask.entity.ActivityTaskEntity;
import com.hwj.basic.server.activitytask.mapper.ActivityTaskMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class ActivityTaskManager extends HwjBaseManager<ActivityTaskMapper, ActivityTaskEntity, ActivityTaskEntity> {
    @Override
    protected QueryWrapper<ActivityTaskEntity> buildQueryWrapper(ActivityTaskEntity params) {
        if (Objects.isNull(params)) {
            return new QueryWrapper<>();
        }
        QueryWrapper<ActivityTaskEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()), ActivityTaskEntity::getId,params.getId())
                .like(Objects.nonNull(params.getName()), ActivityTaskEntity::getName,params.getName())
                .eq(Objects.nonNull(params.getActivityId()), ActivityTaskEntity::getActivityId,params.getActivityId())
                .eq(Objects.nonNull(params.getStatus()), ActivityTaskEntity::getStatus,params.getStatus());
        return wrapper;
    }
}
