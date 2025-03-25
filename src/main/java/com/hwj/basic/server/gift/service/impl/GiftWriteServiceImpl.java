package com.hwj.basic.server.gift.service.impl;

import com.hwj.basic.common.gift.dto.GiftDTO;
import com.hwj.basic.common.gift.service.GiftWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.gift.converter.GiftEntityConverter;
import com.hwj.basic.server.gift.manager.GiftEntityManager;
import com.hwj.basic.server.member.entity.GiftEntity;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class GiftWriteServiceImpl implements GiftWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiftWriteServiceImpl.class);

    @Resource
    private GiftEntityManager giftEntityManager;

    @Resource
    private GiftEntityConverter giftEntityConverter;


    @Override
    public RpcResult<GiftDTO> create(GiftDTO giftDTO) {
        if (Objects.isNull(giftDTO)){
            LOGGER.warn("Gift Create Failded: GiftDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        GiftEntity entity = giftEntityConverter.from(giftDTO);
        try {
            boolean success = giftEntityManager.insert(entity);
            if (!success){
                LOGGER.warn("Gift Create Failded: Insert Database Failed");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            GiftDTO data = giftEntityConverter.toDTO(entity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("Gift Create Failded: System Exception Failed,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<GiftDTO> update(GiftDTO giftDTO) {
        if (Objects.isNull(giftDTO)){
            LOGGER.warn("Gift Update Failded: GiftDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = giftDTO.getId();
        if (id == null){
            LOGGER.warn("Gift Update Failded: Gift(Id) Not Exists");
            return ErrorCode.UPDATE_FAILED.toRpcResult();
        }
        GiftEntity params = new GiftEntity();
        params.setId(id);

        try {
            boolean success = giftEntityManager.updateById(params);
            if (!success){
                LOGGER.warn("Gift Update Failded: Update Database Failed");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }
            GiftDTO data = giftEntityConverter.toDTO(params);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("Gift Update Failded: System Exception Failed,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<GiftDTO> delete(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("Gift Delete Failded: id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        try {
            boolean success = giftEntityManager.deletedById(id);
            if (!success){
                LOGGER.warn("Gift Delete Failded: Delete Database Failed");
                return ErrorCode.DELETE_FAILED.toRpcResult();
            }
            return RpcResult.success();
        }catch (Exception e){
            LOGGER.error("Gift Delete Failded: System Exception Failed,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

}
