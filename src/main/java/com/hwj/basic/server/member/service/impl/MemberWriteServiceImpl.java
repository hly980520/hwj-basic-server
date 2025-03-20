package com.hwj.basic.server.member.service.impl;

import com.hwj.basic.common.member.domain.Member;
import com.hwj.basic.common.member.service.MemberReadService;
import com.hwj.basic.common.member.service.MemberWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.member.converter.MemberEntityConverter;
import com.hwj.basic.server.member.entity.MemberEntity;
import com.hwj.basic.server.member.manager.MemberEntityManager;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Program: hwj
 * @Description:
 * @author: wenjing.huang
 * @since: 2025-03-17 17:55:33
 */
@DubboService(provider = DubboConst.PROVIDER)
public class MemberWriteServiceImpl implements MemberWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberWriteServiceImpl.class);

    @Resource
    private MemberEntityManager memberEntityManager;

    @Resource
    private MemberEntityConverter memberEntityConverter;

    @Resource
    private MemberReadService memberReadService;

    @Override
    public RpcResult<Member> create(Member member) {
        if (Objects.isNull(member)) {
            LOGGER.warn("Create Failed, The member is null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        //用户账号和绑定手机号不能重复
        RpcResult<Member> accountCheck = memberReadService.queryByLoginAccount(member.getLoginAccount());
        RpcResult<Member> phoneCheck = memberReadService.queryByPhoneNumber(member.getCountryCode(), member.getPhoneNumber());
        if (accountCheck.isSuccess()){
            LOGGER.warn("Create Failed: loginAccount already exists, account={}",member.getLoginAccount());
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        if (phoneCheck.isSuccess()){
            LOGGER.warn("Create Failed: phone already registered, countryCode={}, phone={}",member.getCountryCode(), member.getPhoneNumber());
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        //密码加密
        String encryptedPassword = BCrypt.hashpw(member.getLoginPassword(), BCrypt.gensalt());
        member.setLoginPassword(encryptedPassword);

        MemberEntity data = memberEntityConverter.from(member);
        boolean insert = memberEntityManager.insert(data);
        if (!insert) {
            LOGGER.warn("Insert failed [{}]", data);
            return ErrorCode.INSERT_FAILED.toRpcResult();
        }

        Member domain = memberEntityConverter.toDomain(data);
        return RpcResult.success(domain);
    }

    @Override
    public RpcResult<Member> update(Member member) {
        if (Objects.isNull(member)) {
            LOGGER.warn("Update Failed, The member is null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        if (Objects.isNull(member.getId())) {
            LOGGER.warn("The member id is null [{}]", member);
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        MemberEntity data = memberEntityConverter.from(member);
        boolean update = memberEntityManager.updateById(data);
        if (!update) {
            LOGGER.warn("Update failed [{}]", data);
            return ErrorCode.INSERT_FAILED.toRpcResult();
        }

        data = memberEntityManager.selectById(member.getId());
        Member domain = memberEntityConverter.toDomain(data);
        return RpcResult.success(domain);
    }
}
