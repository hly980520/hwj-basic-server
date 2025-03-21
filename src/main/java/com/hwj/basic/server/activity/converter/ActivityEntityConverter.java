package com.hwj.basic.server.activity.converter;

import com.hwj.basic.common.activity.dto.ActivityDTO;
import com.hwj.basic.server.activity.entity.ActivityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityEntityConverter {

    ActivityEntity from(ActivityDTO activityDTO);

    ActivityDTO toDTO(ActivityEntity activityEntity);

    List<ActivityDTO> toDTOList(List<ActivityEntity> entities);
}
