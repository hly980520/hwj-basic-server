package com.hwj.basic.server.membergift.converter;

import com.hwj.basic.common.membergift.dto.MemberGiftDTO;
import com.hwj.basic.server.membergift.entity.MemberGiftEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberGiftEntityConverter {

    MemberGiftEntity from(MemberGiftDTO memberGiftDTO);

    MemberGiftDTO toDTO(MemberGiftEntity memberGiftEntity);

}
