package com.hwj.basic.server.membermissionchecklog.service.impl;

import com.hwj.basic.common.membermissionchecklog.dto.MemberMissionCheckLogDTO;
import com.hwj.basic.common.membermissionchecklog.service.MemberMissionCheckWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membermissionchecklog.converter.MemberMissionCheckEntityConverter;
import com.hwj.basic.server.membermissionchecklog.entity.MemberMissionCheckLogEntity;
import com.hwj.basic.server.membermissionchecklog.manager.MemberMissionCheckLogManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberMissionCheckWriteServiceImpl implements MemberMissionCheckWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberMissionCheckWriteServiceImpl.class);

    @Resource
    private MemberMissionCheckLogManager memberMissionCheckLogManager;

    @Resource
    private MemberMissionCheckEntityConverter memberMissionCheckEntityConverter;

    @Override
    public RpcResult<MemberMissionCheckLogDTO> create(MemberMissionCheckLogDTO memberMissionCheckLogDTO) {
        if (Objects.isNull(memberMissionCheckLogDTO)){
            LOGGER.warn("MemberMissionCheckLog Create Failed: memberMissionCheckLogDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        MemberMissionCheckLogEntity entity = memberMissionCheckEntityConverter.from(memberMissionCheckLogDTO);

        try {
            boolean success = memberMissionCheckLogManager.insert(entity);
            if (!success){
                LOGGER.warn("MemberMissionCheckLog Create Failed: Insert Database Failed");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            MemberMissionCheckLogDTO data = memberMissionCheckEntityConverter.toDTO(entity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MemberMissionCheckLog Create Failed: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }

    }

    @Override
    public RpcResult<MemberMissionCheckLogDTO> update(MemberMissionCheckLogDTO memberMissionCheckLogDTO) {
        if (Objects.isNull(memberMissionCheckLogDTO)){
            LOGGER.warn("MemberMissionCheckLog Update Failed: memberMissionCheckLogDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = memberMissionCheckLogDTO.getId();
        try {
            MemberMissionCheckLogEntity checkExists = memberMissionCheckLogManager.selectById(id);
            if (checkExists == null){
                LOGGER.warn("MemberMissionCheckLog Update Failed: MemberMissionCheckLog(Id) Not Exists");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }
            MemberMissionCheckLogEntity entity = memberMissionCheckEntityConverter.from(memberMissionCheckLogDTO);
            entity.setId(id);

            boolean success = memberMissionCheckLogManager.updateById(entity);
            if (!success){
                LOGGER.warn("MemberMissionCheckLog Update Failed: Update Database Failed");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }

            MemberMissionCheckLogEntity lastEntity = memberMissionCheckLogManager.selectById(id);
            MemberMissionCheckLogDTO data = memberMissionCheckEntityConverter.toDTO(lastEntity);

            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MemberMissionCheckLog Update Failed: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<MemberMissionCheckLogDTO> delete(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MemberMissionCheckLog Delete Failed: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MemberMissionCheckLog Delete Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        boolean success = memberMissionCheckLogManager.deletedById(id);
        if (!success){
            LOGGER.warn("MemberMissionCheckLog Delete Failed: Delete Database Failed");
            return ErrorCode.DELETE_FAILED.toRpcResult();
        }
        return RpcResult.success();
    }
}
