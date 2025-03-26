package com.hwj.basic.server.membergift.service.impl;

import com.hwj.basic.common.membergift.dto.MemberGiftDTO;
import com.hwj.basic.common.membergift.service.MemberGiftWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membergift.converter.MemberGiftEntityConverter;
import com.hwj.basic.server.membergift.manager.MemberGiftEntityManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberGiftWriteServiceImpl implements MemberGiftWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberGiftWriteServiceImpl.class);

    @Resource
    private MemberGiftEntityManager memberGiftEntityManager;

    @Resource
    private MemberGiftEntityConverter memberGiftEntityConverter;


    @Override
    public RpcResult<MemberGiftDTO> create(MemberGiftDTO memberGiftDTO) {
        if (Objects.isNull(memberGiftDTO)){
            LOGGER.warn("MemberGift Create Failded: memberGiftDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        return null;
    }

    @Override
    public RpcResult<MemberGiftDTO> update(MemberGiftDTO memberGiftDTO) {
        return null;
    }

    @Override
    public RpcResult<MemberGiftDTO> delete(Long id) {
        return null;
    }
}
