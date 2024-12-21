package com.dearnote.service.member;

import com.dearnote.domain.Letter;
import com.dearnote.domain.LetterBox;
import com.dearnote.domain.Member;
import com.dearnote.web.dto.OAuth2.MemberDTO;
import org.springframework.data.domain.Page;

public interface MemberQueryService {

    Member getMember(Long memberId);

    // 전체 편지함 조회
    Page<Letter> getAllLetterList(Long memberId, Integer page);

    // 받은 편지함 조회
    Page<Letter> getReceivedLetterList(Long memberId, Integer page);

    // 보낸 편지함 조회
    Page<Letter> getSentLetterList(Long memberId, Integer page);

    // 내게 쓴 편지함 조회
    Page<Letter> getSelfLetterList(Long memberId, Integer page);

    // 저장한 편지함 조회
    Page<Letter> getMarkedLetterList(Long memberId, Integer page);

    // 이메일로 수신자 지정
    Member getMemberByEmail(String Email);

    Member registerMember(MemberDTO.JoinDTO joinDTO);
    // Member 객체 생성 메서드
    Member createMember(MemberDTO.JoinDTO joinDTO);
    // LetterBox 생성 메서드
    LetterBox createLetterBox();
}
