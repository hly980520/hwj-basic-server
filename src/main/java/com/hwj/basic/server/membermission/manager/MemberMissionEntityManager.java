package com.hwj.basic.server.membermission.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.membermission.entity.MemberMissionEntity;
import com.hwj.basic.server.membermission.mapper.MemberMissionMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class MemberMissionEntityManager extends HwjBaseManager<MemberMissionMapper, MemberMissionEntity,MemberMissionEntity> {
    @Override
    protected QueryWrapper<MemberMissionEntity> buildQueryWrapper(MemberMissionEntity params) {
        if (Objects.isNull(params)) {
            return new QueryWrapper<>();
        }
        QueryWrapper<MemberMissionEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),MemberMissionEntity::getId,params.getId())
                .eq(Objects.nonNull(params.getMissionId()),MemberMissionEntity::getMissionId,params.getMissionId())
                .eq(Objects.nonNull(params.getMemberId()),MemberMissionEntity::getMemberId,params.getMemberId());
        return wrapper;
    }
}
