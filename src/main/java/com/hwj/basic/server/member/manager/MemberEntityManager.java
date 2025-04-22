package com.hwj.basic.server.member.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.common.member.params.MemberQuery;
import com.hwj.basic.server.member.entity.MemberEntity;
import com.hwj.basic.server.member.mapper.MemberEntityMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * @Program: hwj
 * @Description:
 * @author: wenjing.huang
 * @since: 2025-03-17 15:16:53
 */
@Repository
public class MemberEntityManager extends HwjBaseManager<MemberEntityMapper, MemberEntity, MemberQuery> {

    @Override
    protected QueryWrapper<MemberEntity> buildQueryWrapper(MemberQuery params) {
        if (Objects.isNull(params)) {
            return new QueryWrapper<>();
        }
        QueryWrapper<MemberEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()), MemberEntity::getId, params.getId())
                .like(Objects.nonNull(params.getNickname()), MemberEntity::getNickname, params.getNickname())
                .eq(Objects.nonNull(params.getLoginAccount()), MemberEntity::getLoginAccount, params.getLoginAccount())
                .eq(StringUtils.isNotBlank(params.getCountryCode()), MemberEntity::getCountryCode, params.getCountryCode())
                .eq(Objects.nonNull(params.getPhoneNumber()), MemberEntity::getPhoneNumber, params.getPhoneNumber())
                .ge(Objects.nonNull(params.getCreatedDateStart()), MemberEntity::getCreatedDate, params.getCreatedDateStart())
                .le(Objects.nonNull(params.getCreatedDateEnd()), MemberEntity::getCreatedDate, params.getCreatedDateEnd());
        return wrapper;
    }
}
