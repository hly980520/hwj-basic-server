package com.hwj.basic.server.membermission.converter;

import com.hwj.basic.common.membermission.dto.MemberMissionDTO;
import com.hwj.basic.server.membermission.entity.MemberMissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMissionEntityConverter {

    MemberMissionEntity from(MemberMissionDTO memberMissionDTO);

    MemberMissionDTO toDTO(MemberMissionEntity memberMissionEntity);
}
