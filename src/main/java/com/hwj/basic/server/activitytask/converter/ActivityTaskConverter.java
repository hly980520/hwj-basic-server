package com.hwj.basic.server.activitytask.converter;

import com.hwj.basic.common.activitytask.dto.ActivityTaskDTO;
import com.hwj.basic.server.activitytask.entity.ActivityTaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityTaskConverter {

    ActivityTaskEntity from(ActivityTaskDTO activityTaskDTO);

    ActivityTaskDTO toDTO(ActivityTaskEntity activityTaskEntity);

}
