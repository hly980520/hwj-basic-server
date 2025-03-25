package com.hwj.basic.server.messageeventconfig.service.impl;

import com.hwj.basic.common.messageeventconfig.dto.MessageEventDTO;
import com.hwj.basic.common.messageeventconfig.service.MessageEventReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import com.hwj.basic.server.messageeventconfig.converter.MessageEventEntityConverter;
import com.hwj.basic.server.messageeventconfig.entity.MessageEventEntity;
import com.hwj.basic.server.messageeventconfig.manager.MessageEventManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class MessageEventReadServiceImpl implements MessageEventReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageEventReadServiceImpl.class);

    @Resource
    private MessageEventManager messageEventManager;

    @Resource
    private MessageEventEntityConverter messageEventEntityConverter;


    @Override
    public RpcResult<MessageEventDTO> queryById(Long id) {
        if (Objects.isNull(id)){
            LOGGER.warn("MessageEventConfig QueryById Failded: Id Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        if (id <= 0){
            LOGGER.warn("MessageEventConfig QueryById Failded: Id Is Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MessageEventEntity entity = messageEventManager.selectById(id);
        MessageEventDTO data = messageEventEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MessageEventDTO> queryByEventName(String eventName) {
        if (StringUtils.isEmpty(eventName)){
            LOGGER.warn("MessageEventConfig QueryByEventName Failded: EventName Is Null Or Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MessageEventEntity params = new MessageEventEntity();
        params.setEventName(eventName);
        MessageEventEntity entity = messageEventManager.selectOne(params);
        MessageEventDTO data = messageEventEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<MessageEventDTO> queryByEventCode(String eventCode) {
        if (StringUtils.isEmpty(eventCode)){
            LOGGER.warn("MessageEventConfig QueryByEventCode Failded: EventCode Is Null Or Invalid");
            return ErrorCode.PARAMS_INVALID.toRpcResult();
        }
        MessageEventEntity params = new MessageEventEntity();
        params.setEventCode(eventCode);
        MessageEventEntity entity = messageEventManager.selectOne(params);
        MessageEventDTO data = messageEventEntityConverter.toDTO(entity);
        return RpcResult.success(data);
    }

    @Override
    public RpcResult<DataPage<MessageEventDTO>> queryPage(DataPage<MessageEventDTO> dataPage, MessageEventDTO messageEventDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(messageEventDTO)) {
            LOGGER.warn("dataPage Is Null Or messageEventDTO Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }

        dataPage = messageEventManager.selectPage(dataPage,
                messageEventEntityConverter.from(messageEventDTO),
                this::toMessageList
        );

        return RpcResult.success(dataPage);
    }

    /**
     * 转messageList
     * @param list MessageEventEntity List
     * @return List<MessageEventDTO>
     */
    private List<MessageEventDTO> toMessageList(List<MessageEventEntity> list) {
        return list.stream()
                .map(data -> messageEventEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
