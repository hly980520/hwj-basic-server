package com.hwj.basic.server.membergift.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.member.entity.MemberEntity;
import com.hwj.basic.server.membergift.entity.MemberGiftEntity;
import com.hwj.basic.server.membergift.mapper.MemberGiftEntityMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class MemberGiftEntityManager extends HwjBaseManager<MemberGiftEntityMapper, MemberGiftEntity,MemberGiftEntity> {
    @Override
    protected QueryWrapper<MemberGiftEntity> buildQueryWrapper(MemberGiftEntity params) {
        if (Objects.isNull(params)) {
            return new QueryWrapper<>();
        }
        QueryWrapper<MemberGiftEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),MemberGiftEntity::getId, params.getId())
                .eq(Objects.nonNull(params.getMemberId()),MemberGiftEntity::getMemberId, params.getMemberId())
                .eq(Objects.nonNull(params.getGiftType()),MemberGiftEntity::getGiftType, params.getGiftType())
                .eq(Objects.nonNull(params.getStatus()),MemberGiftEntity::getStatus, params.getStatus());
        return wrapper;
    }
}
