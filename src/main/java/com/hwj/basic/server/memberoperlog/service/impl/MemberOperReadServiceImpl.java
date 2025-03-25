package com.hwj.basic.server.memberoperlog.service.impl;

import com.hwj.basic.common.memberoperlog.dto.MemberOperDTO;
import com.hwj.basic.common.memberoperlog.service.MemberOperReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.memberoperlog.converter.MemberOperEntityConverter;
import com.hwj.basic.server.memberoperlog.entity.MemberOperEntity;
import com.hwj.basic.server.memberoperlog.manager.MemberOperManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class MemberOperReadServiceImpl implements MemberOperReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberOperReadServiceImpl.class);

    @Resource
    private MemberOperManager memberOperManager;

    @Resource
    private MemberOperEntityConverter memberOperEntityConverter;

    @Override
    public RpcResult<MemberOperDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MemberOperLog QueryById Failed: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MemberOperLog QueryById Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MemberOperEntity entity = memberOperManager.selectById(id);
        MemberOperDTO data = memberOperEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberOperDTO> queryByMemberId(Long memberId) {
        if (Objects.isNull(memberId)){
            LOGGER.warn("MemberOperLog QueryByMemberId Failed: MemberId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (memberId <= 0){
            LOGGER.warn("MemberOperLog QueryByMemberId Failed: MemberId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        MemberOperEntity params = new MemberOperEntity();
        params.setMemberId(memberId);
        MemberOperEntity entity = memberOperManager.selectOne(params);
        MemberOperDTO data = memberOperEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberOperDTO> queryByOperType(String operType) {
        if (Objects.isNull(operType)){
            LOGGER.warn("MemberOperLog QueryByOperType Failed: OperType Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        MemberOperEntity params = new MemberOperEntity();
        params.setOperationType(operType);
        MemberOperEntity entity = memberOperManager.selectOne(params);
        MemberOperDTO data = memberOperEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MemberOperDTO> queryByDate(LocalDateTime createdDate, LocalDateTime updatedDate) {
        if (Objects.isNull(createdDate) || Objects.isNull(updatedDate)){
            LOGGER.warn("MemberOperLog QueryByDate Failed: CreatedDate Or UpdatedDate Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        MemberOperEntity params = new MemberOperEntity();
        params.setCreatedDate(createdDate);
        params.setUpdatedDate(updatedDate);

        MemberOperEntity entity = memberOperManager.selectOne(params);
        MemberOperDTO data = memberOperEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<MemberOperDTO>> queryPage(DataPage<MemberOperDTO> dataPage, MemberOperDTO memberOperDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(memberOperDTO)) {
            LOGGER.warn("dataPage Is Null Or memberOperDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        dataPage = memberOperManager.selectPage(dataPage,
                memberOperEntityConverter.from(memberOperDTO),
                this::toMemberOperList
        );
        return RpcResult.success(dataPage);
    }

    /**
     * 转 MemberOperList
     * @param list
     * @return
     */
    private List<MemberOperDTO> toMemberOperList(List<MemberOperEntity> list){
        return list.stream()
                .map(data -> memberOperEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
