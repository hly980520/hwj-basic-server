package com.hwj.basic.server.gift.service.impl;

import com.hwj.basic.common.gift.dto.GiftDTO;
import com.hwj.basic.common.gift.service.GiftReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.gift.converter.GiftEntityConverter;
import com.hwj.basic.server.gift.manager.GiftEntityManager;
import com.hwj.basic.server.member.entity.GiftEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class GiftReadServiceImpl implements GiftReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiftReadServiceImpl.class);

    @Resource
    private GiftEntityManager giftEntityManager;

    @Resource
    private GiftEntityConverter giftEntityConverter;


    @Override
    public RpcResult<GiftDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("Gift QueryById Failed: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("Gift QueryById Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        GiftEntity entity = giftEntityManager.selectById(id);
        GiftDTO data = giftEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<GiftDTO> queryByName(String name) {
        if (StringUtils.isEmpty(name)){
            LOGGER.warn("Gift QueryByName Failed: name Is Null Or Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        GiftEntity params = new GiftEntity();
        params.setName(name);
        GiftEntity entity = giftEntityManager.selectOne(params);
        GiftDTO data = giftEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<GiftDTO> queryByGiftType(Integer giftType) {
        if (Objects.isNull(giftType)){
            LOGGER.warn("Gift QuueryByGiftType Failed: giftType Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        GiftEntity params = new GiftEntity();
        params.setGiftType(giftType);
        GiftEntity entity = giftEntityManager.selectOne(params);
        GiftDTO data = giftEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<GiftDTO>> queryPage(DataPage<GiftDTO> dataPage, GiftDTO giftDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(giftDTO)) {
            LOGGER.warn("dataPage Is Null Or giftDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        dataPage = giftEntityManager.selectPage(dataPage,
                giftEntityConverter.from(giftDTO),
                this::toGiftList
        );
        return RpcResult.success(dataPage);
    }

    /**
     * 转 toGiftList
     * @param list GiftEntity list
     * @return
     */
    private List<GiftDTO> toGiftList(List<GiftEntity> list){
        return list.stream()
                .map(data -> giftEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
