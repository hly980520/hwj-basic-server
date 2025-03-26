package com.hwj.basic.server.membergift.service.impl;

import com.hwj.basic.common.membergift.dto.MemberGiftDTO;
import com.hwj.basic.common.membergift.service.MemberGiftReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.membergift.converter.MemberGiftEntityConverter;
import com.hwj.basic.server.membergift.entity.MemberGiftEntity;
import com.hwj.basic.server.membergift.manager.MemberGiftEntityManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberGiftReadServiceImpl implements MemberGiftReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberGiftReadServiceImpl.class);

    @Resource
    private MemberGiftEntityManager memberGiftEntityManager;

    @Resource
    private MemberGiftEntityConverter memberGiftEntityConverter;

    @Override
    public RpcResult<MemberGiftDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MemberGift QueryById Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MemberGift QueryById Failded: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        MemberGiftEntity entity = memberGiftEntityManager.selectById(id);
        MemberGiftDTO data = memberGiftEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberGiftDTO> queryByMemberId(Long memberId) {
        if (Objects.isNull(memberId)){
            LOGGER.warn("MemberGift QueryByMemberId Failded: memberId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (memberId <= 0){
            LOGGER.warn("MemberGift QueryByMemberId Failded: memberId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        MemberGiftEntity params = new MemberGiftEntity();
        params.setMemberId(memberId);
        MemberGiftEntity entity = memberGiftEntityManager.selectOne(params);
        MemberGiftDTO data = memberGiftEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberGiftDTO> queryByGiftId(Long giftId) {
        if (Objects.isNull(giftId)){
            LOGGER.warn("MemberGift QueryByGiftId Failded: giftId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (giftId <= 0){
            LOGGER.warn("MemberGift QueryByGiftId Failded: giftId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        MemberGiftEntity params = new MemberGiftEntity();
        params.setGiftId(giftId);
        MemberGiftEntity entity = memberGiftEntityManager.selectOne(params);
        MemberGiftDTO data = memberGiftEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<MemberGiftDTO>> queryPage(DataPage<MemberGiftDTO> dataPage, MemberGiftDTO memberGiftDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(memberGiftDTO)) {
            LOGGER.warn("dataPage Is Null Or memberGiftDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        dataPage = memberGiftEntityManager.selectPage(dataPage,
                memberGiftEntityConverter.from(memberGiftDTO),
                this::toMemberGiftList
        );

        return RpcResult.success(dataPage);
    }

    /**
     * 转 memberGiftEntity
     * @param list  MemberGiftEntity list
     * @return List<MemberGiftDTO>
     */
    private List<MemberGiftDTO> toMemberGiftList(List<MemberGiftEntity> list){
        return list.stream()
                .map(data -> memberGiftEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }
}
