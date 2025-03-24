package com.hwj.basic.server.activitymember.service.impl;

import com.hwj.basic.common.activitymember.dto.ActivityMemberDTO;
import com.hwj.basic.common.activitymember.service.ActivityMemberReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.activitymember.converter.ActivityMemberEntityConverter;
import com.hwj.basic.server.activitymember.entity.ActivityMemberEntity;
import com.hwj.basic.server.activitymember.manager.ActivityMemberEntityManager;
import com.hwj.basic.server.constant.DubboConst;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class ActivityMemberReadServiceImpl implements ActivityMemberReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityMemberReadServiceImpl.class);

    @Resource
    private ActivityMemberEntityManager activityMemberEntityManager;

    @Resource
    private ActivityMemberEntityConverter activityMemberEntityConverter;

    @Override
    public RpcResult<ActivityMemberDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("ActivityMember QueryById: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("ActivityMember QueryById:Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        ActivityMemberEntity entity = activityMemberEntityManager.selectById(id);
        ActivityMemberDTO data = activityMemberEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<ActivityMemberDTO> queryByMemberId(Long memberId) {
        if (Objects.isNull(memberId)){
            LOGGER.warn("ActivityMember QueryByMemberId: MemberId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (memberId <= 0){
            LOGGER.warn("ActivityMember QueryByMemberId: MemberId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        ActivityMemberEntity params = new ActivityMemberEntity();
        params.setMemberId(memberId);
        ActivityMemberEntity entity = activityMemberEntityManager.selectOne(params);
        ActivityMemberDTO data = activityMemberEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<ActivityMemberDTO> queryByActivityId(Long activityId) {
        if (Objects.isNull(activityId)){
            LOGGER.warn("ActivityMember QueryByActivityId Failed: ActivityId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (activityId <= 0){
            LOGGER.warn("ActivityMember QueryByActivityId Failed: ActivityId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        ActivityMemberEntity params = new ActivityMemberEntity();
        params.setActivityId(activityId);
        ActivityMemberEntity entity = activityMemberEntityManager.selectOne(params);
        ActivityMemberDTO data = activityMemberEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }


    @Override
    public RpcResult<DataPage<ActivityMemberDTO>> queryPage(DataPage<ActivityMemberDTO> dataPage, ActivityMemberDTO activityMemberDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(activityMemberDTO)) {
            LOGGER.warn("dataPage Is Null Or activityMemberDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        dataPage = activityMemberEntityManager.selectPage(dataPage,
                activityMemberEntityConverter.from(activityMemberDTO),
                this::toActivityMemberList
        );
        return RpcResult.success(dataPage);
    }

    /**
     * 转activityMemberList
     * @param list ActivityMemberEntity list
     * @return List<ActivityMemberDTO>
     */
    private List<ActivityMemberDTO> toActivityMemberList(List<ActivityMemberEntity> list) {
        return list.stream()
                .map(data -> activityMemberEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
