package com.dearnote.service.member;

import com.dearnote.apipayload.code.status.ErrorStatus;
import com.dearnote.apipayload.exception.handler.MemberHandler;
import com.dearnote.domain.Letter;
import com.dearnote.domain.LetterBox;
import com.dearnote.domain.Member;
import com.dearnote.domain.enums.LetterType;
import com.dearnote.repository.LetterRepository;
import com.dearnote.repository.MemberRepository;
import com.dearnote.web.dto.OAuth2.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    private final LetterRepository letterRepository;

    @Override
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }

    @Override
    public Page<Letter> getAllLetterList(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return letterRepository.findAllBySender(member, PageRequest.of(page, 10));
    }

    @Override
    public Page<Letter> getReceivedLetterList(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return letterRepository.findAllBySenderAndType(member, LetterType.RECEIVED, PageRequest.of(page, 10));
    }

    @Override
    public Page<Letter> getSentLetterList(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return letterRepository.findAllBySenderAndType(member, LetterType.SENT, PageRequest.of(page, 10));
    }

    @Override
    public Page<Letter> getSelfLetterList(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return letterRepository.findAllBySenderAndType(member, LetterType.SELF, PageRequest.of(page, 10));
    }

    @Override
    public Page<Letter> getMarkedLetterList(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return letterRepository.findAllBySenderAndMarkTrue(member, PageRequest.of(page, 10));
    }

    @Override
    public Member getMemberByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.EMAIL_MEMBER_NOT_FOUND));
    }


    @Transactional
    @Override
    public Member registerMember(MemberDTO.JoinDTO joinDTO) {

        Member member = createMember(joinDTO);

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

        letterBox.setMember(member);

        return member;
    }

    @Override
    public LetterBox createLetterBox() {
        // LetterBox 객체를 생성하면서 Member를 필수로 받도록 수정
        return new LetterBox();
    }

}
