package com.dearnote.service;

import com.dearnote.domain.LetterBox;
import com.dearnote.domain.Member;
import com.dearnote.web.dto.OAuth2.MemberDTO;

public interface MemberQueryService {

    Member registerMember(MemberDTO.JoinDTO joinDTO);

    // Member 객체 생성 메서드
    Member createMember(MemberDTO.JoinDTO joinDTO);

    // LetterBox 생성 메서드
    LetterBox createLetterBox();
}
