package com.hwj.basic.server.membermissionreward.service.impl;

import com.hwj.basic.common.membermissionreward.dto.MemberMissionRewardDTO;
import com.hwj.basic.common.membermissionreward.service.MemberMissionRewardWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membermission.converter.MemberMissionEntityConverter;
import com.hwj.basic.server.membermission.entity.MemberMissionEntity;
import com.hwj.basic.server.membermissionreward.converter.MemberMissionRewardEntityConverter;
import com.hwj.basic.server.membermissionreward.entity.MemberMissionRewardEntity;
import com.hwj.basic.server.membermissionreward.manager.MemberMissionRewardManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberMissionRewardWriteServiceImpl implements MemberMissionRewardWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberMissionRewardWriteServiceImpl.class);

    @Resource
    private MemberMissionRewardManager memberMissionRewardManager;

    @Resource
    private MemberMissionRewardEntityConverter memberMissionRewardEntityConverter;


    @Override
    public RpcResult<MemberMissionRewardDTO> create(MemberMissionRewardDTO memberMissionRewardDTO) {
        if (Objects.isNull(memberMissionRewardDTO)){
            LOGGER.warn("MemberMissionReward Create Failed: memberMissionRewardDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        MemberMissionRewardEntity entity = memberMissionRewardEntityConverter.from(memberMissionRewardDTO);
        try {
            boolean success = memberMissionRewardManager.insert(entity);
            if (!success){
                LOGGER.warn("MemberMissionReward Create Failed: Insert Database Failed");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            MemberMissionRewardDTO data = memberMissionRewardEntityConverter.toDTO(entity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MemberMissionReward Create Failed: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<MemberMissionRewardDTO> update(MemberMissionRewardDTO memberMissionRewardDTO) {
        if (Objects.isNull(memberMissionRewardDTO)){
            LOGGER.warn("MemberMissionReward Update Failed: memberMissionRewardDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        Long id = memberMissionRewardDTO.getId();

        try {
            MemberMissionRewardEntity checkExists = memberMissionRewardManager.selectById(id);
            if (checkExists == null){
                LOGGER.warn("MemberMissionReward Update Failed: MemberMissionReward(Id) Not Exists");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }
            MemberMissionRewardEntity params = memberMissionRewardEntityConverter.from(memberMissionRewardDTO);
            params.setId(id);
            boolean success = memberMissionRewardManager.updateById(params);
            if (!success){
                LOGGER.warn("MemberMissionReward Update Failed: Update Database Faied");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }
            MemberMissionRewardEntity lastEntity = memberMissionRewardManager.selectById(id);
            MemberMissionRewardDTO data = memberMissionRewardEntityConverter.toDTO(lastEntity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MemberMissionReward Update Failed: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<MemberMissionRewardDTO> delete(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MemberMissionReward Delete Failed: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MemberMissionReward Delete Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        try {
            boolean success = memberMissionRewardManager.deletedById(id);
            if (!success){
                LOGGER.warn("MemberMissionReward Delete Failed: Delete Database Failed");
                return ErrorCode.DELETE_FAILED.toRpcResult();
            }
            return RpcResult.success();
        }catch (Exception e){
            LOGGER.warn("MemberMissionReward Delete Failed: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }
}
