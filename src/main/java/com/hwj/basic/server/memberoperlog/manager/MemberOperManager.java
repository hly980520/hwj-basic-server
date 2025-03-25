package com.hwj.basic.server.memberoperlog.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.memberoperlog.entity.MemberOperEntity;
import com.hwj.basic.server.memberoperlog.mapper.MemberOperMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;

import java.util.Objects;

public class MemberOperManager extends HwjBaseManager<MemberOperMapper, MemberOperEntity,MemberOperEntity> {
    @Override
    protected QueryWrapper<MemberOperEntity> buildQueryWrapper(MemberOperEntity params) {
        if (Objects.isNull(params)){
            return new QueryWrapper<>();
        }
        QueryWrapper<MemberOperEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),MemberOperEntity::getId,params.getId())
                .eq(Objects.nonNull(params.getMemberId()),MemberOperEntity::getMemberId,params.getMemberId())
                .eq(Objects.nonNull(params.getOperationType()),MemberOperEntity::getOperationType,params.getOperationType())
                .ge(Objects.nonNull(params.getCreatedDate()),MemberOperEntity::getCreatedDate,params.getCreatedDate())
                .le(Objects.nonNull(params.getUpdatedDate()),MemberOperEntity::getUpdatedDate,params.getUpdatedDate());
        return wrapper;
    }
}
