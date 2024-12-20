package com.dearnote.service.letterbox;

import com.dearnote.apipayload.code.status.ErrorStatus;
import com.dearnote.apipayload.exception.handler.MemberHandler;
import com.dearnote.domain.LetterBox;
import com.dearnote.repository.LetterBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LetterBoxQueryServiceImpl implements LetterBoxQueryService {


    private final LetterBoxRepository letterBoxRepository;

    @Override
    @Transactional(readOnly = true)
    public LetterBox getLetterBox(Long memberId) {

        System.out.println(memberId);

        return letterBoxRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
