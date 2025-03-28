package com.hwj.basic.server.membermissionchecklog.service.impl;

import com.hwj.basic.common.membermissionchecklog.dto.MemberMissionCheckLogDTO;
import com.hwj.basic.common.membermissionchecklog.service.MemberMissionCheckReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membermissionchecklog.converter.MemberMissionCheckEntityConverter;
import com.hwj.basic.server.membermissionchecklog.entity.MemberMissionCheckLogEntity;
import com.hwj.basic.server.membermissionchecklog.manager.MemberMissionCheckLogManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberMissionCheckReadServiceImpl implements MemberMissionCheckReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberMissionCheckReadServiceImpl.class);

    @Resource
    private MemberMissionCheckLogManager memberMissionCheckLogManager;

    @Resource
    private MemberMissionCheckEntityConverter memberMissionCheckEntityConverter;

    @Override
    public RpcResult<MemberMissionCheckLogDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MemberMissionCheckLog QueryById Failed: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MemberMissionCheckLog QueryById Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MemberMissionCheckLogEntity entity = memberMissionCheckLogManager.selectById(id);
        MemberMissionCheckLogDTO data = memberMissionCheckEntityConverter.toDTO(entity);
        return  RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberMissionCheckLogDTO> queryByMissionId(Long missionId) {
        if (Objects.isNull(missionId)){
            LOGGER.warn("MemberMissionCheckLog QueryByMissionId Failed: missionId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (missionId <= 0){
            LOGGER.warn("MemberMissionCheckLog QueryByMissionId Failed: missionId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        MemberMissionCheckLogEntity params = new MemberMissionCheckLogEntity();
        params.setMissionId(missionId);

        MemberMissionCheckLogEntity entity = memberMissionCheckLogManager.selectOne(params);
        MemberMissionCheckLogDTO data = memberMissionCheckEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberMissionCheckLogDTO> queryByMemberId(Long memberId) {
        if (Objects.isNull(memberId)){
            LOGGER.warn("MemberMissionCheckLog QueryByMemberId Failed: memberId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (memberId <= 0){
            LOGGER.warn("MemberMissionCheckLog QueryByMemberId Failed: memberId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MemberMissionCheckLogEntity params = new MemberMissionCheckLogEntity();
        params.setMemberId(memberId);

        MemberMissionCheckLogEntity entity = memberMissionCheckLogManager.selectOne(params);
        MemberMissionCheckLogDTO data = memberMissionCheckEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<MemberMissionCheckLogDTO>> queryPage(DataPage<MemberMissionCheckLogDTO> dataPage, MemberMissionCheckLogDTO memberMissionCheckLogDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(memberMissionCheckLogDTO)) {
            LOGGER.warn("dataPage Is Null Or memberMissionCheckLogDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        dataPage = memberMissionCheckLogManager.selectPage(dataPage,
                memberMissionCheckEntityConverter.from(memberMissionCheckLogDTO),
                this::toMemberMissionCheckLogList
        );

        return RpcResult.success(dataPage);
    }

    private List<MemberMissionCheckLogDTO> toMemberMissionCheckLogList(List<MemberMissionCheckLogEntity> list) {
        return list.stream()
                .map(data -> memberMissionCheckEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
