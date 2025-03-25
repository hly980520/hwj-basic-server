package com.hwj.basic.server.messagesendlog.converter;

import com.hwj.basic.common.messagesendlog.dto.MessageSendDTO;
import com.hwj.basic.server.messagesendlog.entity.MessageSendEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageSendEntityConverter {

    MessageSendEntity from(MessageSendDTO messageSendDTO);

    MessageSendDTO toDTO(MessageSendEntity messageSendEntity);
}
