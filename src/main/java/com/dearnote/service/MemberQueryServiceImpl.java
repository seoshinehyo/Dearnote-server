package com.dearnote.service;

import com.dearnote.domain.LetterBox;
import com.dearnote.domain.Member;
import com.dearnote.repository.MemberRepository;
import com.dearnote.web.dto.OAuth2.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Member registerMember(MemberDTO.JoinDTO joinDTO) {
        // 1. Member 객체 생성
        Member member = createMember(joinDTO);

        // 2. Member 저장
        memberRepository.save(member);

        return member;
    }

    @Override
    public Member createMember(MemberDTO.JoinDTO joinDTO) {
        // 1. LetterBox 객체 생성
        LetterBox letterBox = createLetterBox();

        // 2. Member 객체 생성
        Member member = Member.builder()
                .name(joinDTO.getName())
                .email(joinDTO.getEmail())
                .username(joinDTO.getUsername())
                .role(joinDTO.getRole())
                .letterBox(letterBox)// Member와 LetterBox 연결
                .build();

        // 3. 양방향 관계 설정: LetterBox의 member 필드에 해당 Member 할당
        letterBox.setMember(member);

        return member;
    }

    @Override
    public LetterBox createLetterBox() {
        // LetterBox 객체를 생성하면서 Member를 필수로 받도록 수정
        return new LetterBox();  // 수정된 부분
    }
}
