package com.hwj.basic.server.membergift.service.impl;

import com.hwj.basic.common.membergift.dto.MemberGiftDTO;
import com.hwj.basic.common.membergift.service.MemberGiftWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membergift.converter.MemberGiftEntityConverter;
import com.hwj.basic.server.membergift.entity.MemberGiftEntity;
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
        MemberGiftEntity entity = memberGiftEntityConverter.from(memberGiftDTO);
        try {
            boolean success = memberGiftEntityManager.insert(entity);
            if (!success){
                LOGGER.warn("MemberGift Create Failded: Insert Database Failed");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            MemberGiftDTO data = memberGiftEntityConverter.toDTO(entity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MemberGift Create Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }

    }

    @Override
    public RpcResult<MemberGiftDTO> update(MemberGiftDTO memberGiftDTO) {
        if (Objects.isNull(memberGiftDTO)){
            LOGGER.warn("MemberGift Update Failded: memberGiftDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = memberGiftDTO.getId();
        if (id == null || id <= 0) {
            LOGGER.warn("MemberGift Update Failed: Id Is Invalid", id);
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        try {
            MemberGiftEntity checkExists = memberGiftEntityManager.selectById(id);
            if (checkExists == null){
                LOGGER.warn("MemberGift Update Failed: MemberGift Not Exists");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }
            MemberGiftEntity params = memberGiftEntityConverter.from(memberGiftDTO);
            params.setId(id);

            boolean success = memberGiftEntityManager.updateById(params);
            if (!success){
                LOGGER.warn("MemberGift Update Failded: Update Database Failed");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }

            MemberGiftEntity lastEntity = memberGiftEntityManager.selectById(id);

            MemberGiftDTO data = memberGiftEntityConverter.toDTO(lastEntity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MemberGift Create Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<MemberGiftDTO> delete(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MemberGift Delete Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MemberGift Delete Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        try {
            boolean success = memberGiftEntityManager.deletedById(id);
            if (!success){
                LOGGER.warn("MemberGift Delete Failded: Delete Database Failed");
                return ErrorCode.DELETE_FAILED.toRpcResult();
            }
            return RpcResult.success();
        }catch (Exception e){
            LOGGER.warn("MemberGift Delete Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }

    }
}
