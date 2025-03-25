package com.hwj.basic.server.messageeventconfig.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hwj.basic.server.member.mapper.MemberEntityMapper;
import com.hwj.basic.server.messageeventconfig.entity.MessageEventEntity;
import com.hwj.basic.server.messageeventconfig.mapper.MessageEventMapper;
import com.hwj.basic.server.support.mybatis.manager.HwjBaseManager;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class MessageEventManager extends HwjBaseManager<MessageEventMapper, MessageEventEntity,MessageEventEntity> {
    @Override
    protected QueryWrapper<MessageEventEntity> buildQueryWrapper(MessageEventEntity params) {
        if (Objects.isNull(params)){
            return new QueryWrapper<>();
        }
        QueryWrapper<MessageEventEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Objects.nonNull(params.getId()),MessageEventEntity::getId,params.getId())
                .like(Objects.nonNull(params.getEventName()),MessageEventEntity::getEventName,params.getEventName())
                .like(Objects.nonNull(params.getEventCode()),MessageEventEntity::getEventCode,params.getEventCode());
        return wrapper;
    }
}
