package com.dearnote.service;

import com.dearnote.domain.Letter;
import com.dearnote.domain.Member;
import com.dearnote.repository.LetterRepository;
import com.dearnote.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;

    private final LetterRepository letterRepository;

    @Override
    public Page<Letter> getLetterList(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        return letterRepository.findAllByReceiver(member, PageRequest.of(page, 10));
    }
}
