package com.hwj.basic.server.messagesendlog.service.impl;

import com.hwj.basic.common.messagesendlog.dto.MessageSendDTO;
import com.hwj.basic.common.messagesendlog.service.MessageSendReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.messagesendlog.converter.MessageSendEntityConverter;
import com.hwj.basic.server.messagesendlog.entity.MessageSendEntity;
import com.hwj.basic.server.messagesendlog.manager.MessageSendManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class MessageSendReadServiceImpl implements MessageSendReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSendReadServiceImpl.class);

    @Resource
    private MessageSendManager messageSendManager;

    @Resource
    private MessageSendEntityConverter messageSendEntityConverter;

    @Override
    public RpcResult<MessageSendDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MessageSendLog QueryById Failed: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MessageSendLog QueryById Failed: Id Is Invaild");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        MessageSendEntity entity = messageSendManager.selectById(id);
        MessageSendDTO data = messageSendEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MessageSendDTO> queryByMemberId(Long memberId) {
        if (Objects.isNull(memberId)){
            LOGGER.warn("MessageSendLog QueryByMemberId Failed: memberId Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (memberId <= 0){
            LOGGER.warn("MessageSendLog QueryByMemberId Failed: memberId Is Invaild");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }

        MessageSendEntity params = new MessageSendEntity();
        params.setMemberId(memberId);
        MessageSendEntity entity = messageSendManager.selectOne(params);
        MessageSendDTO data = messageSendEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MessageSendDTO> queryByPhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)){
            LOGGER.warn("MessageSendLog QueryByPhoneNumber Failed: PhoneNumber Is Null Or Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MessageSendEntity params = new MessageSendEntity();
        params.setPhoneNumber(phoneNumber);
        MessageSendEntity entity = messageSendManager.selectOne(params);
        MessageSendDTO data = messageSendEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<MessageSendDTO>> queryPage(DataPage<MessageSendDTO> dataPage, MessageSendDTO messageSendDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(messageSendDTO)) {
            LOGGER.warn("dataPage Is Null Or MessageSendDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        dataPage = messageSendManager.selectPage(dataPage,
                messageSendEntityConverter.from(messageSendDTO),
                this::toMessageDTOList
        );
        return RpcResult.success(dataPage);
    }

    private List<MessageSendDTO> toMessageDTOList(List<MessageSendEntity> list){
        return list.stream()
                .map(data -> messageSendEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
