package com.hwj.basic.server.membermission.service.impl;

import com.hwj.basic.common.membermission.dto.MemberMissionDTO;
import com.hwj.basic.common.membermission.service.MemberMissionReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membermission.converter.MemberMissionEntityConverter;
import com.hwj.basic.server.membermission.entity.MemberMissionEntity;
import com.hwj.basic.server.membermission.manager.MemberMissionEntityManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberMissionReadServiceImpl implements MemberMissionReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberMissionReadServiceImpl.class);

    @Resource
    private MemberMissionEntityManager memberMissionEntityManager;

    @Resource
    private MemberMissionEntityConverter memberMissionEntityConverter;


    @Override
    public RpcResult<MemberMissionDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MemberMission QueryById Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MemberMission QueryById Failded: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MemberMissionEntity entity = memberMissionEntityManager.selectById(id);
        MemberMissionDTO data = memberMissionEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberMissionDTO> queryByMissionId(Long missionId) {
        if (Objects.isNull(missionId)){
            LOGGER.warn("MemberMission QueryByMissionId Failded: missionId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (missionId <= 0){
            LOGGER.warn("MemberMission QueryByMissionId Failded: missionId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MemberMissionEntity params = new MemberMissionEntity();
        params.setMissionId(missionId);
        MemberMissionEntity entity = memberMissionEntityManager.selectOne(params);
        MemberMissionDTO data = memberMissionEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberMissionDTO> queryByMemberId(Long memberId) {
        if (Objects.isNull(memberId)){
            LOGGER.warn("MemberMission QueryByMemberId Failded: memberId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (memberId <= 0){
            LOGGER.warn("MemberMission QueryByMemberId Failded: memberId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MemberMissionEntity params = new MemberMissionEntity();
        params.setMemberId(memberId);
        MemberMissionEntity entity = memberMissionEntityManager.selectOne(params);
        MemberMissionDTO data = memberMissionEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<MemberMissionDTO>> queryPage(DataPage<MemberMissionDTO> dataPage, MemberMissionDTO memberMissionDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(memberMissionDTO)) {
            LOGGER.warn("dataPage Is Null Or memberMissionDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        dataPage = memberMissionEntityManager.selectPage(dataPage,
                memberMissionEntityConverter.from(memberMissionDTO),
                this::toMemberMissionList
        );

        return RpcResult.success(dataPage);
    }


    private List<MemberMissionDTO> toMemberMissionList(List<MemberMissionEntity> list){
        return list.stream()
                .map(data -> memberMissionEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
