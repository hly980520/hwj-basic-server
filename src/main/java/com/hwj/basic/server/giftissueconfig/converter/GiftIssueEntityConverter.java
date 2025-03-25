package com.hwj.basic.server.giftissueconfig.converter;

import com.hwj.basic.common.giftissueconfig.dto.GiftIssueDTO;
import com.hwj.basic.server.giftissueconfig.entity.GiftIssueEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GiftIssueEntityConverter {

    GiftIssueEntity from(GiftIssueDTO giftIssueDTO);

    GiftIssueDTO toDTO(GiftIssueEntity giftIssueEntity);
}
