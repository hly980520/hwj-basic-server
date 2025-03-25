package com.hwj.basic.server.messagesendlog.mapper;

import com.hwj.basic.server.messagesendlog.entity.MessageSendEntity;
import com.hwj.basic.server.support.mybatis.mapper.HwjBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageSendMapper extends HwjBaseMapper<MessageSendEntity> {
}
