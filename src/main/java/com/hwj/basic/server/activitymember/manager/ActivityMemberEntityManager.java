package com.hwj.basic.server.activitymember.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.activitymember.entity.ActivityMemberEntity;
import com.hwj.basic.server.activitymember.mapper.ActivityMemberEntityMapper;
import com.hwj.basic.server.member.entity.MemberEntity;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class ActivityMemberEntityManager extends HwjBaseManager<ActivityMemberEntityMapper, ActivityMemberEntity,ActivityMemberEntity> {

    @Override
    protected QueryWrapper<ActivityMemberEntity> buildQueryWrapper(ActivityMemberEntity params) {
        if (Objects.isNull(params)) {
            return new QueryWrapper<>();
        }
        QueryWrapper<ActivityMemberEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),ActivityMemberEntity::getId,params.getId())
                .eq(Objects.nonNull(params.getMemberId()),ActivityMemberEntity::getMemberId,params.getMemberId())
                .eq(Objects.nonNull(params.getActivityId()),ActivityMemberEntity::getActivityId,params.getActivityId())
                .eq(Objects.nonNull(params.getStatus()),ActivityMemberEntity::getStatus,params.getStatus());
        return wrapper;
    }
}
