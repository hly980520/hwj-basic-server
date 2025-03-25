package com.hwj.basic.server.giftissueconfig.service.impl;

import com.hwj.basic.common.giftissueconfig.dto.GiftIssueDTO;
import com.hwj.basic.common.giftissueconfig.service.GiftIssueReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.giftissueconfig.converter.GiftIssueEntityConverter;
import com.hwj.basic.server.giftissueconfig.entity.GiftIssueEntity;
import com.hwj.basic.server.giftissueconfig.manager.GiftIssueEntityManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class GiftIssueReadServiceImpl implements GiftIssueReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiftIssueReadServiceImpl.class);

    @Resource
    private GiftIssueEntityManager giftIssueEntityManager;

    @Resource
    private GiftIssueEntityConverter giftIssueEntityConverter;


    @Override
    public RpcResult<GiftIssueDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("GiftIssueConfig QueryById Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("GiftIssueConfig QueryById Failded: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        GiftIssueEntity entity = giftIssueEntityManager.selectById(id);
        GiftIssueDTO data = giftIssueEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<GiftIssueDTO> queryByMemberId(Long memberId) {
        if (Objects.isNull(memberId)){
            LOGGER.warn("GiftIssueConfig QueryByMemberId Failded: memberId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (memberId <= 0){
            LOGGER.warn("GiftIssueConfig QueryByMemberId Failded: memberId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        GiftIssueEntity params = new GiftIssueEntity();
        params.setMemberId(memberId);
        GiftIssueEntity entity = giftIssueEntityManager.selectOne(params);
        GiftIssueDTO data = giftIssueEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<GiftIssueDTO> queryByGiftInfos(String giftInfos) {
        if (StringUtils.isEmpty(giftInfos)){
            LOGGER.warn("GiftIssueConfig QueryByGiftInfos Failded: giftInfos Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        GiftIssueEntity params = new GiftIssueEntity();
        params.setGiftInfos(giftInfos);
        GiftIssueEntity entity = giftIssueEntityManager.selectOne(params);
        GiftIssueDTO data = giftIssueEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<GiftIssueDTO>> queryPage(DataPage<GiftIssueDTO> dataPage, GiftIssueDTO giftIssueDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(giftIssueDTO)) {
            LOGGER.warn("dataPage Is Null Or giftIssueDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        dataPage = giftIssueEntityManager.selectPage(dataPage,
                giftIssueEntityConverter.from(giftIssueDTO),
                this::toGiftIssueList
        );

        return RpcResult.success(dataPage);
    }

    /**
     * 转 giftIssueList
     * @param list GiftIssueEntity list
     * @return List<GiftIssueDTO>
     */
    private List<GiftIssueDTO> toGiftIssueList(List<GiftIssueEntity> list) {
        return list.stream()
                .map(data -> giftIssueEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
