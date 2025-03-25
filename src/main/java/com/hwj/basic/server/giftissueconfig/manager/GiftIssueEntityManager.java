package com.hwj.basic.server.giftissueconfig.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.giftissueconfig.entity.GiftIssueEntity;
import com.hwj.basic.server.giftissueconfig.mapper.GiftIssueEntityMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class GiftIssueEntityManager extends HwjBaseManager<GiftIssueEntityMapper, GiftIssueEntity,GiftIssueEntity> {
    @Override
    protected QueryWrapper<GiftIssueEntity> buildQueryWrapper(GiftIssueEntity params) {
        if (Objects.isNull(params)) {
            return new QueryWrapper<>();
        }
        QueryWrapper<GiftIssueEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),GiftIssueEntity::getId, params.getId())
                .eq(Objects.nonNull(params.getMemberId()),GiftIssueEntity::getMemberId,params.getMemberId())
                .like(Objects.nonNull(params.getGiftInfos()),GiftIssueEntity::getGiftInfos,params.getGiftInfos());
        return wrapper;
    }
}
