package com.hwj.basic.server.member.service.impl;

import com.hwj.basic.common.member.domain.Member;
import com.hwj.basic.common.member.params.MemberQuery;
import com.hwj.basic.common.member.service.MemberReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.member.converter.MemberEntityConverter;
import com.hwj.basic.server.member.entity.MemberEntity;
import com.hwj.basic.server.member.manager.MemberEntityManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Program: hwj
 * @Description:
 * @author: wenjing.huang
 * @since: 2025-03-17 15:19:04
 */
@DubboService(provider = DubboConst.PROVIDER)
public class MemberReadServiceImpl implements MemberReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberReadServiceImpl.class);

    @Resource
    private MemberEntityManager memberEntityManager;

    @Resource
    private MemberEntityConverter memberEntityConverter;

    @Override
    public RpcResult<Member> queryById(Long id) {
        if (Objects.isNull(id)) {
            LOGGER.warn("Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        MemberEntity memberEntity = memberEntityManager.selectById(id);
        Member data = memberEntityConverter.toDomain(memberEntity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<Member> queryByLoginAccount(String loginAccount) {
        if (StringUtils.isBlank(loginAccount)) {
            LOGGER.warn("loginAccount Is Blank");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        MemberQuery params = new MemberQuery();
        params.setLoginAccount(loginAccount);
        MemberEntity memberEntity = memberEntityManager.selectOne(params);
        Member data = memberEntityConverter.toDomain(memberEntity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<Member> queryByPhoneNumber(String countryCode, String phoneNumber) {
        if (StringUtils.isBlank(countryCode) || StringUtils.isBlank(phoneNumber)) {
            LOGGER.warn("CountryCode Is Blank Or phoneNumber Is Blank");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        MemberQuery params = new MemberQuery();
        params.setCountryCode(countryCode);
        params.setPhoneNumber(phoneNumber);
        MemberEntity memberEntity = memberEntityManager.selectOne(params);
        Member data = memberEntityConverter.toDomain(memberEntity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<Member>> queryPage(DataPage<Member> dataPage, MemberQuery params) {
        if (Objects.isNull(dataPage) || Objects.isNull(params)) {
            LOGGER.warn("dataPage Is Null Or params Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        dataPage = memberEntityManager.selectPage(dataPage, params, this::toMemberList);

        return RpcResult.success(dataPage);
    }

    /**
     * 转memberList
     * @param list MemberEntity List
     * @return List<Member>
     */
    private List<Member> toMemberList(List<MemberEntity> list) {
        return list.stream()
                .map(data -> memberEntityConverter.toDomain(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
