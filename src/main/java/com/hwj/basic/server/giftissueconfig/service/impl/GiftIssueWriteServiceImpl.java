package com.hwj.basic.server.giftissueconfig.service.impl;

import com.hwj.basic.common.giftissueconfig.dto.GiftIssueDTO;
import com.hwj.basic.common.giftissueconfig.service.GiftIssueWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.giftissueconfig.converter.GiftIssueEntityConverter;
import com.hwj.basic.server.giftissueconfig.entity.GiftIssueEntity;
import com.hwj.basic.server.giftissueconfig.manager.GiftIssueEntityManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class GiftIssueWriteServiceImpl implements GiftIssueWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GiftIssueWriteServiceImpl.class);

    @Resource
    private GiftIssueEntityManager giftIssueEntityManager;

    @Resource
    private GiftIssueEntityConverter giftIssueEntityConverter;

    @Override
    public RpcResult<GiftIssueDTO> create(GiftIssueDTO giftIssueDTO) {
        if (Objects.isNull(giftIssueDTO)){
            LOGGER.warn("GiftIssueConfig Create Failded: giftIssueDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        GiftIssueEntity entity = giftIssueEntityConverter.from(giftIssueDTO);
        try {
            boolean success = giftIssueEntityManager.insert(entity);
            if (!success){
                LOGGER.warn("GiftIssueConfig Create Failded: Insert Database Failed");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            GiftIssueDTO data = giftIssueEntityConverter.toDTO(entity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("GiftIssueConfig Create Failded: System Exception Failed,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<GiftIssueDTO> update(GiftIssueDTO giftIssueDTO) {
        if (Objects.isNull(giftIssueDTO)){
            LOGGER.warn("GiftIssueConfig Update Failded: giftIssueDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = giftIssueDTO.getId();
        if (Objects.isNull(id) || id <= 0){
            LOGGER.warn("GiftIssueConfig Update Failded: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        try {
            GiftIssueEntity checkExists = giftIssueEntityManager.selectById(id);
            if (checkExists == null){
                LOGGER.warn("GiftIssueConfig Update Failded: GiftIssue(Id) Not Exists");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }

            GiftIssueEntity params = giftIssueEntityConverter.from(giftIssueDTO);
            params.setId(id);
            boolean succcess = giftIssueEntityManager.updateById(params);
            if (!succcess){
                LOGGER.warn("GiftIssueConfig Update Failded: Update Database Failed");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            GiftIssueEntity lastEntity = giftIssueEntityManager.selectById(id);
            GiftIssueDTO data = giftIssueEntityConverter.toDTO(lastEntity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("GiftIssueConfig Update Failded: System Exception Failed,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<GiftIssueDTO> delete(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("GiftIssueConfig Delete Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("GiftIssueConfig Delete Failded: id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        try {
            boolean success = giftIssueEntityManager.deletedById(id);
            if (!success){
                LOGGER.warn("GiftIssueConfig Delete Failded: Delete Database Failed");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            return RpcResult.success();
        }catch (Exception e){
            LOGGER.error("GiftIssueConfig Delete Failded: System Exception Failed,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }
}
