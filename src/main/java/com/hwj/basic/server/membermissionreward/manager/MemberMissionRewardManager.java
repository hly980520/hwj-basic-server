package com.hwj.basic.server.membermissionreward.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.membermissionreward.entity.MemberMissionRewardEntity;
import com.hwj.basic.server.membermissionreward.mapper.MemberMissionRewardMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class MemberMissionRewardManager extends HwjBaseManager<MemberMissionRewardMapper, MemberMissionRewardEntity,MemberMissionRewardEntity> {
    @Override
    protected QueryWrapper<MemberMissionRewardEntity> buildQueryWrapper(MemberMissionRewardEntity params) {
        if (Objects.isNull(params)){
            return new QueryWrapper<>();
        }
        QueryWrapper<MemberMissionRewardEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),MemberMissionRewardEntity::getId,params.getId())
                .eq(Objects.nonNull(params.getActivityTaskId()),MemberMissionRewardEntity::getActivityTaskId,params.getActivityTaskId())
                .eq(Objects.nonNull(params.getMemberId()),MemberMissionRewardEntity::getMemberId,params.getMemberId());
        return wrapper;
    }
}
