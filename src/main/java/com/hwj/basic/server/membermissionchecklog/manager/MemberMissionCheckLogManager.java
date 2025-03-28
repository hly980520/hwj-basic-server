package com.hwj.basic.server.membermissionchecklog.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.membermissionchecklog.entity.MemberMissionCheckLogEntity;
import com.hwj.basic.server.membermissionchecklog.mapper.MemberMissionCheckLogMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;

import javax.annotation.Resource;
import java.util.Objects;

@Resource
public class MemberMissionCheckLogManager extends HwjBaseManager<MemberMissionCheckLogMapper, MemberMissionCheckLogEntity, MemberMissionCheckLogEntity> {
    @Override
    protected QueryWrapper<MemberMissionCheckLogEntity> buildQueryWrapper(MemberMissionCheckLogEntity params) {
        if (Objects.isNull(params)){
            return new QueryWrapper<>();
        }
        QueryWrapper<MemberMissionCheckLogEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),MemberMissionCheckLogEntity::getId,params.getId())
                .eq(Objects.nonNull(params.getMissionId()),MemberMissionCheckLogEntity::getMissionId,params.getMissionId())
                .eq(Objects.nonNull(params.getMemberId()),MemberMissionCheckLogEntity::getMemberId,params.getMemberId());
        return wrapper;
    }
}
