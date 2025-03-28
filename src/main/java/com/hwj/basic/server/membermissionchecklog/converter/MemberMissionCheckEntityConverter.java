package com.hwj.basic.server.membermissionchecklog.converter;

import com.hwj.basic.common.membermissionchecklog.dto.MemberMissionCheckLogDTO;
import com.hwj.basic.server.membermissionchecklog.entity.MemberMissionCheckLogEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMissionCheckEntityConverter {

    MemberMissionCheckLogEntity from(MemberMissionCheckLogDTO memberMissionCheckLogDTO);

    MemberMissionCheckLogDTO toDTO(MemberMissionCheckLogEntity memberMissionCheckLogEntity);

}
