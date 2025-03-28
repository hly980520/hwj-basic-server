package com.hwj.basic.server.messagesendlog.service.impl;

import com.hwj.basic.common.messagesendlog.dto.MessageSendDTO;
import com.hwj.basic.common.messagesendlog.service.MessageSendWriteService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.messagesendlog.converter.MessageSendEntityConverter;
import com.hwj.basic.server.messagesendlog.entity.MessageSendEntity;
import com.hwj.basic.server.messagesendlog.manager.MessageSendManager;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Objects;

@DubboService(provider = DubboConst.PROVIDER)
public class MessageSendWriteServiceImpl implements MessageSendWriteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSendWriteServiceImpl.class);

    @Resource
    private MessageSendManager messageSendManager;

    @Resource
    private MessageSendEntityConverter messageSendEntityConverter;

    @Override
    public RpcResult<MessageSendDTO> create(MessageSendDTO messageSendDTO) {
        if (Objects.isNull(messageSendDTO)){
            LOGGER.warn("MessageSendLog Create Failded: MessageSendDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        // 检查用户ID
        if (Objects.isNull(messageSendDTO.getMemberId()) || messageSendDTO.getMemberId() <= 0) {
            LOGGER.warn("MessageSendLog Create Failed: MemberId Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        MessageSendEntity params = messageSendEntityConverter.from(messageSendDTO);
        try {
            boolean success = messageSendManager.insert(params);
            if (!success){
                LOGGER.error("MessageSendLog Create Failded: Insert Database Failded");
                return ErrorCode.INSERT_FAILED.toRpcResult();
            }
            MessageSendDTO data = messageSendEntityConverter.toDTO(params);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MessageSendLog Create Failded: System Exception,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }

    @Override
    public RpcResult<MessageSendDTO> update(MessageSendDTO messageSendDTO) {
        if (Objects.isNull(messageSendDTO)){
            LOGGER.warn("MessageSendLog Update Failded: MessageSendDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        Long id = messageSendDTO.getId();
        if (Objects.isNull(id) || id <= 0){
            LOGGER.warn("MessageSendLog Update Failded: Id Is Invalid,[id={}]",id);
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        try {
            MessageSendEntity checkExists = messageSendManager.selectById(id);
            if (checkExists == null){
                LOGGER.warn("MessageSendLog Update Failded: MessageSendLog(Id) Not Exists");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }

            MessageSendEntity params = messageSendEntityConverter.from(messageSendDTO);
            params.setId(id);
            boolean success = messageSendManager.updateById(params);
            if (!success){
                LOGGER.warn("MessageSendLog Update Failded: Update Database Failded");
                return ErrorCode.UPDATE_FAILED.toRpcResult();
            }

            MessageSendEntity lastEntity = messageSendManager.selectById(id);
            MessageSendDTO data = messageSendEntityConverter.toDTO(lastEntity);
            return RpcResult.success(data);
        }catch (Exception e){
            LOGGER.error("MessageSendLog Update Failded: System Exception Failded,[{}]",e.getMessage());
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }

    }

    @Override
    public RpcResult<MessageSendDTO> delete(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MessageSendLog Delete Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MessageSendLog Delete Failed: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        try {
            boolean success = messageSendManager.deletedById(id);
            if (!success){
                LOGGER.warn("MessageSendLog Delete Failded: Delete Database Faild");
                return ErrorCode.DELETE_FAILED.toRpcResult();
            }
            return RpcResult.success();
        }catch (Exception e){
            LOGGER.error("MessageSendLog Delete Failded: System Exception Failed");
            return ErrorCode.SYSTEM_EXCEPTION.toRpcResult();
        }
    }
}
