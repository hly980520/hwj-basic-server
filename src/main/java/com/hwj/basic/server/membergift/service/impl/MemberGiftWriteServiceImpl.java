package com.hwj.basic.server.membergift.service.impl;

import com.hwj.basic.common.membergift.service.MemberGiftWriteService;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membergift.converter.MemberGiftEntityConverter;
import com.hwj.basic.server.membergift.manager.MemberGiftEntityManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberGiftWriteServiceImpl implements MemberGiftWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberGiftWriteServiceImpl.class);

    @Resource
    private MemberGiftEntityManager memberGiftEntityManager;

    @Resource
    private MemberGiftEntityConverter memberGiftEntityConverter;


}
