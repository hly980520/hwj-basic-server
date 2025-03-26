package com.hwj.basic.server.memberoperlog.service.impl;

import com.hwj.basic.common.memberoperlog.dto.MemberOperDTO;
import com.hwj.basic.common.memberoperlog.service.MemberOperWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.memberoperlog.converter.MemberOperEntityConverter;
import com.hwj.basic.server.memberoperlog.entity.MemberOperEntity;
import com.hwj.basic.server.memberoperlog.manager.MemberOperManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberOperWriteServiceImpl implements MemberOperWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberOperWriteServiceImpl.class);

    @Resource
    private MemberOperManager memberOperManager;

    @Resource
    private MemberOperEntityConverter memberOperEntityConverter;

    @Override
    public RpcResult<MemberOperDTO> create(MemberOperDTO memberOperDTO) {
        if(Objects.isNull(memberOperDTO)){
            LOGGER.warn("MemberOperLog Create Failded: MemberOperDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        MemberOperEntity entity = memberOperEntityConverter.from(memberOperDTO);
        try {
            boolean success = memberOperManager.insert(entity);
            if (!success){
                LOGGER.warn("MemberOperLog Create Failded: Insert Database Failed");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            MemberOperDTO data = memberOperEntityConverter.toDTO(entity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MemberOperLog Create Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<MemberOperDTO> update(MemberOperDTO memberOperDTO) {
        if(Objects.isNull(memberOperDTO)){
            LOGGER.warn("MemberOperLog Update Failded: MemberOperDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = memberOperDTO.getId();
        if (Objects.isNull(id) || id <= 0){
            LOGGER.warn("MemberOperLog Update Failded: MemberOperLog(Id) Not Exists");
            return ErrorCode.UPDATE_FAILED.toRpcResult();
        }
        MemberOperEntity params = new MemberOperEntity();
        params.setId(id);
        try {
            boolean success = memberOperManager.updateById(params);
            if (!success){
                LOGGER.warn("MemberOperLog Update Failded: Update Database Failed");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            MemberOperDTO data = memberOperEntityConverter.toDTO(params);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MemberOperLog Update Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<MemberOperDTO> delete(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MemberOperLog Delete Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MemberOperLog Delete Failded: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        try {
            boolean success = memberOperManager.deletedById(id);
            if (!success){
                LOGGER.warn("MemberOperLog Delete Failded: Delete Database Failed");
                return ErrorCode.DELETE_FAILED.toRpcResult();
            }
            return RpcResult.success();
        }catch (Exception e){
            LOGGER.error("MemberOperLog Delete Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }
}
