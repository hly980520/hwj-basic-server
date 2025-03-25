package com.hwj.basic.server.membergift.service.impl;

import com.hwj.basic.common.membergift.dto.MemberGiftDTO;
import com.hwj.basic.common.membergift.service.MemberGiftReadService;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membergift.converter.MemberGiftEntityConverter;
import com.hwj.basic.server.membergift.manager.MemberGiftEntityManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberGiftReadServiceImpl implements MemberGiftReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberGiftReadServiceImpl.class);

    @Resource
    private MemberGiftEntityManager memberGiftEntityManager;

    @Resource
    private MemberGiftEntityConverter memberGiftEntityConverter;

    @Override
    public RpcResult<DataPage<MemberGiftDTO>> queryPage(DataPage<MemberGiftDTO> dataPage, MemberGiftDTO memberGiftDTO) {
        return null;
    }
}
