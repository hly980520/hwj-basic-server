package com.hwj.basic.server.activitymember.converter;

import com.hwj.basic.common.activitymember.dto.ActivityMemberDTO;
import com.hwj.basic.server.activity.entity.ActivityEntity;
import com.hwj.basic.server.activitymember.entity.ActivityMemberEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMemberEntityConverter {

    ActivityMemberEntity from(ActivityMemberDTO activityMemberDTO);

    ActivityMemberDTO toDTO(ActivityMemberEntity activityMemberEntity);
}
