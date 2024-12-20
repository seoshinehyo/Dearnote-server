package com.dearnote.service.letterbox;

import com.dearnote.apipayload.code.status.ErrorStatus;
import com.dearnote.apipayload.exception.handler.MemberHandler;
import com.dearnote.domain.LetterBox;
import com.dearnote.domain.Member;
import com.dearnote.repository.LetterBoxRepository;
import com.dearnote.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LetterBoxCommandServiceImpl implements LetterBoxCommandService {

    private final MemberRepository memberRepository;

    private final LetterBoxRepository letterBoxRepository;

    @Override
    @Transactional
    public LetterBox createLetterBox(Long memberId) {

        // 이미 존재하는 LetterBox가 있는지 먼저 확인
        Optional<LetterBox> existingLetterBox = letterBoxRepository.findByMemberId(memberId);
        if (existingLetterBox.isPresent()) {
            return existingLetterBox.get();
        }

        try {
            // Member 조회
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

            // 새로운 LetterBox 생성
            LetterBox letterBox = LetterBox.builder()
                    .member(member)
                    .build();

            return letterBoxRepository.save(letterBox);
        } catch (Exception e) {
            // 중복 데이터로 인해 예외 발생 시 기존 LetterBox를 반환
            return letterBoxRepository.findByMemberId(memberId)
                    .orElseThrow(() -> new IllegalStateException("LetterBox creation failed and no existing record found"));
        }
    }
}
