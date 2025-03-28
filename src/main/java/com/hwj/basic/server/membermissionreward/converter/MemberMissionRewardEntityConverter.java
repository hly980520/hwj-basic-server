package com.hwj.basic.server.membermissionreward.converter;

import com.hwj.basic.common.membermissionreward.dto.MemberMissionRewardDTO;
import com.hwj.basic.server.membermissionreward.entity.MemberMissionRewardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMissionRewardEntityConverter {

    MemberMissionRewardEntity from(MemberMissionRewardDTO memberMissionRewardDTO);

    MemberMissionRewardDTO toDTO(MemberMissionRewardEntity memberMissionRewardEntity);
}
