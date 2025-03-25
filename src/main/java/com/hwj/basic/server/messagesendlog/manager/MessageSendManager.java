package com.hwj.basic.server.messagesendlog.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.messagesendlog.entity.MessageSendEntity;
import com.hwj.basic.server.messagesendlog.mapper.MessageSendMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class MessageSendManager extends HwjBaseManager<MessageSendMapper, MessageSendEntity,MessageSendEntity> {
    @Override
    protected QueryWrapper<MessageSendEntity> buildQueryWrapper(MessageSendEntity params) {
        if (Objects.isNull(params)){
            return new QueryWrapper<>();
        }
        QueryWrapper<MessageSendEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),MessageSendEntity::getId,params.getId())
                .eq(Objects.nonNull(params.getMemberId()),MessageSendEntity::getMemberId,params.getMemberId())
                .like(Objects.nonNull(params.getPhoneNumber()),MessageSendEntity::getPhoneNumber,params.getPhoneNumber());
        return wrapper;
    }
}
