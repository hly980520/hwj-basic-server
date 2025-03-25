package com.hwj.basic.server.messageeventconfig.converter;

import com.hwj.basic.common.messageeventconfig.dto.MessageEventDTO;
import com.hwj.basic.server.messageeventconfig.entity.MessageEventEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageEventEntityConverter {

    MessageEventEntity from(MessageEventDTO messageEventDTO);

    MessageEventDTO toDTO(MessageEventEntity messageEventEntity);

}
