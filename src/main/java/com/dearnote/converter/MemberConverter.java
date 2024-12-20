package com.dearnote.converter;

import com.dearnote.domain.Member;
import com.dearnote.web.dto.OAuth2.MemberDTO;



public class MemberConverter {

    public static Member toMember(MemberDTO.JoinDTO request) {



        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .role(request.getRole())
                .build();
    }
}
