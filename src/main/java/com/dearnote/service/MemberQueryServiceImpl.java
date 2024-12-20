package com.dearnote.service;

import com.dearnote.domain.Letter;
import com.dearnote.domain.Member;
import com.dearnote.domain.enums.LetterStatus;
import com.dearnote.domain.enums.LetterType;
import com.dearnote.repository.LetterRepository;
import com.dearnote.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{
    private final MemberRepository memberRepository;

    private final LetterRepository letterRepository;

    @Override
    public Page<Letter> getAllLetterList(Long memberId, Integer page){
        final Logger logger = LoggerFactory.getLogger(MemberQueryServiceImpl.class);


        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        return letterRepository.findAllBySender(member, PageRequest.of(page, 10));
    }

    @Override
    public Page<Letter> getReceivedLetterList(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        return letterRepository.findAllBySenderAndType(member, LetterType.RECEIVED, PageRequest.of(page, 10));
    }

    @Override
    public Page<Letter> getSentLetterList(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        return letterRepository.findAllBySenderAndType(member, LetterType.SENT, PageRequest.of(page, 10));
    }

    @Override
    public Page<Letter> getSelfLetterList(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        return letterRepository.findAllBySenderAndType(member, LetterType.SELF, PageRequest.of(page, 10));
    }

    @Override
    public Page<Letter> getMarkedLetterList(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        return letterRepository.findAllBySenderAndMarkTrue(member, PageRequest.of(page, 10));
    }
    //
}
