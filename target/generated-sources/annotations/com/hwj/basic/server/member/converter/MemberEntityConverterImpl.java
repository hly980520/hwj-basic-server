package com.hwj.basic.server.member.converter;

import com.alibaba.fastjson2.JSONObject;
import com.hwj.basic.common.member.domain.Member;
import com.hwj.basic.server.member.entity.MemberEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-18T22:25:33+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_351 (Oracle Corporation)"
)
@Component
public class MemberEntityConverterImpl implements MemberEntityConverter {

    @Override
    public MemberEntity from(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setId( member.getId() );
        memberEntity.setNickname( member.getNickname() );
        memberEntity.setLoginAccount( member.getLoginAccount() );
        memberEntity.setLoginPassword( member.getLoginPassword() );
        memberEntity.setCountryCode( member.getCountryCode() );
        memberEntity.setPhoneNumber( member.getPhoneNumber() );
        memberEntity.setRegisterWay( member.getRegisterWay() );
        memberEntity.setRegisterCredentials( member.getRegisterCredentials() );
        JSONObject jSONObject = member.getFeatures();
        if ( jSONObject != null ) {
            memberEntity.setFeatures( new JSONObject( jSONObject ) );
        }
        memberEntity.setCreatedDate( member.getCreatedDate() );
        memberEntity.setCreatedBy( member.getCreatedBy() );
        memberEntity.setUpdatedDate( member.getUpdatedDate() );
        memberEntity.setUpdatedBy( member.getUpdatedBy() );

        return memberEntity;
    }

    @Override
    public Member toDomain(MemberEntity memberEntity) {
        if ( memberEntity == null ) {
            return null;
        }

        Member member = new Member();

        member.setId( memberEntity.getId() );
        member.setNickname( memberEntity.getNickname() );
        member.setLoginAccount( memberEntity.getLoginAccount() );
        member.setLoginPassword( memberEntity.getLoginPassword() );
        member.setCountryCode( memberEntity.getCountryCode() );
        member.setPhoneNumber( memberEntity.getPhoneNumber() );
        member.setRegisterWay( memberEntity.getRegisterWay() );
        member.setRegisterCredentials( memberEntity.getRegisterCredentials() );
        JSONObject jSONObject = memberEntity.getFeatures();
        if ( jSONObject != null ) {
            member.setFeatures( new JSONObject( jSONObject ) );
        }
        member.setCreatedDate( memberEntity.getCreatedDate() );
        member.setCreatedBy( memberEntity.getCreatedBy() );
        member.setUpdatedDate( memberEntity.getUpdatedDate() );
        member.setUpdatedBy( memberEntity.getUpdatedBy() );

        return member;
    }
}
