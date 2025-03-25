package com.hwj.basic.server.gift.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.gift.mapper.GiftMapper;
import com.hwj.basic.server.member.entity.GiftEntity;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class GiftEntityManager extends HwjBaseManager<GiftMapper, GiftEntity,GiftEntity> {
    @Override
    protected QueryWrapper<GiftEntity> buildQueryWrapper(GiftEntity params) {
        if (Objects.isNull(params)) {
            return new QueryWrapper<>();
        }
        QueryWrapper<GiftEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),GiftEntity::getId, params.getId())
                .like(Objects.nonNull(params.getName()), GiftEntity::getName, params.getName())
                .eq(Objects.nonNull(params.getGiftType()),GiftEntity::getGiftType, params.getGiftType());
        return wrapper;
    }
}
