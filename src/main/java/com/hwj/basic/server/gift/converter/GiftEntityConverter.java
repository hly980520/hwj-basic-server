package com.hwj.basic.server.gift.converter;

import com.hwj.basic.common.gift.dto.GiftDTO;
import com.hwj.basic.server.member.entity.GiftEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiftEntityConverter {

    GiftEntity from(GiftDTO giftDTO);

    GiftDTO toDTO(GiftEntity giftEntity);

}
