package com.hwj.basic.server.activityrule.converter;

import com.hwj.basic.common.activityrule.dto.ActivityRuleTextDTO;
import com.hwj.basic.server.activityrule.entity.ActivityRuleTextEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityRuleTextEntityConverter {

    ActivityRuleTextEntity from(ActivityRuleTextDTO activityRuleTextDTO);

    ActivityRuleTextDTO toDTO(ActivityRuleTextEntity activityRuleTextEntity);
}
