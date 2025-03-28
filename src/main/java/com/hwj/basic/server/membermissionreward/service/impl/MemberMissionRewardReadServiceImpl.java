package com.hwj.basic.server.membermissionreward.service.impl;

import com.hwj.basic.common.membermissionreward.dto.MemberMissionRewardDTO;
import com.hwj.basic.common.membermissionreward.service.MemberMissionRewardReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membermission.converter.MemberMissionEntityConverter;
import com.hwj.basic.server.membermissionreward.converter.MemberMissionRewardEntityConverter;
import com.hwj.basic.server.membermissionreward.entity.MemberMissionRewardEntity;
import com.hwj.basic.server.membermissionreward.manager.MemberMissionRewardManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberMissionRewardReadServiceImpl implements MemberMissionRewardReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberMissionRewardReadServiceImpl.class);

    @Resource
    private MemberMissionRewardManager memberMissionRewardManager;

    @Resource
    private MemberMissionRewardEntityConverter memberMissionRewardEntityConverter;

    @Override
    public RpcResult<MemberMissionRewardDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MemberMissionReward QueryById Failed: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MemberMissionReward QueryById Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        MemberMissionRewardEntity entity = memberMissionRewardManager.selectById(id);
        MemberMissionRewardDTO data = memberMissionRewardEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberMissionRewardDTO> queryByActivityTaskId(Long activityTaskId) {
        if (Objects.isNull(activityTaskId)){
            LOGGER.warn("MemberMissionReward QueryByActivityTaskId Failed: activityTaskId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (activityTaskId <= 0){
            LOGGER.warn("MemberMissionReward QueryByActivityTaskId Failed: activityTaskId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MemberMissionRewardEntity params = new MemberMissionRewardEntity();
        params.setActivityTaskId(activityTaskId);

        MemberMissionRewardEntity entity = memberMissionRewardManager.selectOne(params);
        MemberMissionRewardDTO data = memberMissionRewardEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberMissionRewardDTO> queryByMemberId(Long memberId) {
        if (Objects.isNull(memberId)){
            LOGGER.warn("MemberMissionReward QueryByMemberId Failed: memberId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (memberId <= 0){
            LOGGER.warn("MemberMissionReward QueryByMemberId Failed: memberId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MemberMissionRewardEntity params = new MemberMissionRewardEntity();
        params.setMemberId(memberId);

        MemberMissionRewardEntity entity = memberMissionRewardManager.selectOne(params);
        MemberMissionRewardDTO data = memberMissionRewardEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<MemberMissionRewardDTO>> queryPage(DataPage<MemberMissionRewardDTO> dataPage, MemberMissionRewardDTO memberMissionRewardDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(memberMissionRewardDTO)) {
            LOGGER.warn("dataPage Is Null Or memberMissionRewardDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        dataPage = memberMissionRewardManager.selectPage(dataPage,
                memberMissionRewardEntityConverter.from(memberMissionRewardDTO),
                this::toMemberMissionRewardList
        );

        return RpcResult.success(dataPage);
    }

    private List<MemberMissionRewardDTO> toMemberMissionRewardList(List<MemberMissionRewardEntity> list){
        return list.stream()
                .map(data -> memberMissionRewardEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
