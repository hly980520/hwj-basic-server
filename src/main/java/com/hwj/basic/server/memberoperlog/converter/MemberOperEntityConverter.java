package com.hwj.basic.server.memberoperlog.converter;

import com.hwj.basic.common.memberoperlog.dto.MemberOperDTO;
import com.hwj.basic.server.memberoperlog.entity.MemberOperEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberOperEntityConverter {

    MemberOperEntity from(MemberOperDTO memberOperDTO);

    MemberOperDTO toDTO(MemberOperEntity memberOperEntity);

}
