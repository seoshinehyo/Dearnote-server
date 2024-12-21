package com.dearnote.service.member;

import com.dearnote.apipayload.code.status.ErrorStatus;
import com.dearnote.apipayload.exception.handler.MemberHandler;
import com.dearnote.domain.Letter;
import com.dearnote.domain.Member;
import com.dearnote.domain.enums.LetterType;
import com.dearnote.repository.LetterRepository;
import com.dearnote.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }

}
