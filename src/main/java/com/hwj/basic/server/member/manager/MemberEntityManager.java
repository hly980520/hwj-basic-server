package com.hwj.basic.server.member.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.member.entity.MemberEntity;
import com.hwj.basic.server.member.mapper.MemberEntityMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * @Program: hwj
 * @Description:
 * @author: wenjing.huang
 * @since: 2025-03-17 15:16:53
 */
@Repository
public class MemberEntityManager extends HwjBaseManager<MemberEntityMapper, MemberEntity,MemberEntity> {

    @Override
    protected QueryWrapper<MemberEntity> buildQueryWrapper(MemberEntity params) {
        if (Objects.isNull(params)) {
            return new QueryWrapper<>();
        }
        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),MemberEntity::getId, params.getId())
                .like(Objects.nonNull(params.getNickname()), MemberEntity::getNickname, params.getNickname())
                .eq(Objects.nonNull(params.getLoginAccount()),MemberEntity::getLoginAccount, params.getLoginAccount())
                .eq(Objects.nonNull(params.getPhoneNumber()),MemberEntity::getPhoneNumber, params.getPhoneNumber());
        return wrapper;
    }
}
