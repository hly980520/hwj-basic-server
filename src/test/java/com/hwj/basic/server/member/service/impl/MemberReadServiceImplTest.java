package com.hwj.basic.server.member.service.impl;

import com.hwj.basic.common.member.domain.Member;
import com.hwj.basic.common.member.service.MemberReadService;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.BaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

class MemberReadServiceImplTest extends BaseTest {

    @Resource
    private MemberReadService memberReadService;

    @Test
    void queryById() {
        Long id = 1L;
        RpcResult<Member> memberRpcResult = memberReadService.queryById(id);
        logger.info("{}", memberRpcResult);
    }

    @Test
    void queryByLoginAccount() {
    }

    @Test
    void queryByPhoneNumber() {
    }

    @Test
    void queryPage() {
    }
}