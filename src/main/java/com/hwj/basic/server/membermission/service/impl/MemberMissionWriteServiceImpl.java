package com.hwj.basic.server.membermission.service.impl;

import com.hwj.basic.common.membermission.dto.MemberMissionDTO;
import com.hwj.basic.common.membermission.service.MemberMissionWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membermission.converter.MemberMissionEntityConverter;
import com.hwj.basic.server.membermission.entity.MemberMissionEntity;
import com.hwj.basic.server.membermission.manager.MemberMissionEntityManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberMissionWriteServiceImpl implements MemberMissionWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberMissionWriteServiceImpl.class);

    @Resource
    private MemberMissionEntityManager memberMissionEntityManager;

    @Resource
    private MemberMissionEntityConverter memberMissionEntityConverter;


    @Override
    public RpcResult<MemberMissionDTO> create(MemberMissionDTO memberMissionDTO) {
        if (Objects.isNull(memberMissionDTO)){
            LOGGER.warn("MemberMission Create Failded: MemberMissionDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        MemberMissionEntity entity = memberMissionEntityConverter.from(memberMissionDTO);
        try {
            boolean success = memberMissionEntityManager.insert(entity);
            if (!success){
                LOGGER.warn("MemberMission Create Failded: Insert Database Failded");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            MemberMissionDTO data = memberMissionEntityConverter.toDTO(entity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MemberMission Create Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<MemberMissionDTO> update(MemberMissionDTO memberMissionDTO) {
        if (Objects.isNull(memberMissionDTO)){
            LOGGER.warn("MemberMission Update Failded: MemberMissionDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = memberMissionDTO.getId();
        MemberMissionEntity checkExists = memberMissionEntityManager.selectById(id);
        if (checkExists == null){
            LOGGER.warn("MemberMission Update Failded: MemberMission(Id) Not Exists");
            return ErrorCode.UPDATE_FAILED.toRpcResult();
        }
        MemberMissionEntity params = memberMissionEntityConverter.from(memberMissionDTO);
        params.setId(id);

        boolean success = memberMissionEntityManager.updateById(params);
        if (!success){
            LOGGER.warn("MemberMission Update Failded: Update Database Failded");
            return ErrorCode.UPDATE_FAILED.toRpcResult();
        }

        MemberMissionEntity lastEntity = memberMissionEntityManager.selectById(id);
        MemberMissionDTO data = memberMissionEntityConverter.toDTO(lastEntity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberMissionDTO> delete(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MemberMission Delete Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MemberMission Delete Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        try {
            boolean success = memberMissionEntityManager.deletedById(id);
            if (!success){
                LOGGER.warn("MemberMission Delete Failded: Delete Database Failded");
                return ErrorCode.DELETE_FAILED.toRpcResult();
            }
            return RpcResult.success();
        }catch (Exception e){
            LOGGER.error("MemberMission Delete Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }
}
