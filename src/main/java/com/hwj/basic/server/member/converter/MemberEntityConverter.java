package com.hwj.basic.server.member.converter;

import com.hwj.basic.common.member.domain.Member;
import com.hwj.basic.server.member.entity.MemberEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberEntityConverter {

    MemberEntity from(Member member);

    Member toDomain(MemberEntity memberEntity);
}
