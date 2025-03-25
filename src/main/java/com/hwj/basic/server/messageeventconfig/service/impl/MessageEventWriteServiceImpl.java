package com.hwj.basic.server.messageeventconfig.service.impl;

import com.hwj.basic.common.messageeventconfig.dto.MessageEventDTO;
import com.hwj.basic.common.messageeventconfig.service.MessageEventWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.messageeventconfig.converter.MessageEventEntityConverter;
import com.hwj.basic.server.messageeventconfig.entity.MessageEventEntity;
import com.hwj.basic.server.messageeventconfig.manager.MessageEventManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class MessageEventWriteServiceImpl implements MessageEventWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageEventReadServiceImpl.class);

    @Resource
    private MessageEventManager messageEventManager;

    @Resource
    private MessageEventEntityConverter messageEventEntityConverter;


    @Override
    public RpcResult<MessageEventDTO> create(MessageEventDTO messageEventDTO) {
        if (Objects.isNull(messageEventDTO)){
            LOGGER.warn("MessageEventConfig Create Failded: MessageEventDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        MessageEventEntity entity = messageEventEntityConverter.from(messageEventDTO);
        try {
            boolean success = messageEventManager.insert(entity);
            if (!success){
                LOGGER.warn("MessageEventConfig Create Failded: Insert Database Failed");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            MessageEventDTO data = messageEventEntityConverter.toDTO(entity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MessageEventConfig Create Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<MessageEventDTO> update(MessageEventDTO messageEventDTO) {
        if (Objects.isNull(messageEventDTO)){
            LOGGER.warn("MessageEventConfig Update Failded: MessageEventDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = messageEventDTO.getId();
        if (Objects.isNull(id) || id <= 0){
            LOGGER.warn("MessageEventConfig Update Failded: MessageEvent(Id) Not Exists");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MessageEventEntity params = new MessageEventEntity();
        params.setId(id);

        try {
            boolean success = messageEventManager.updateById(params);
            if (!success){
                LOGGER.warn("MessageEventConfig Update Failded: Update Database Failded");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }
            MessageEventDTO data = messageEventEntityConverter.toDTO(params);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MessageEventConfig Update Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<MessageEventDTO> delete(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MessageEventConfig Delete Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        try {
            boolean success = messageEventManager.deletedById(id);
            if (!success){
                LOGGER.warn("MessageEventConfig Delete Failded: Delete Database Failed");
                return ErrorCode.DELETE_FAILED.toRpcResult();
            }
            return RpcResult.success();
        }catch (Exception e){
            LOGGER.error("MessageEventConfig Delete Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

}
