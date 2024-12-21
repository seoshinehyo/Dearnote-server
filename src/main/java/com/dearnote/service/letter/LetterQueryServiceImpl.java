package com.dearnote.service.letter;

import com.dearnote.apipayload.code.status.ErrorStatus;
import com.dearnote.apipayload.exception.handler.LetterHandler;
import com.dearnote.domain.Letter;
import com.dearnote.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LetterQueryServiceImpl implements LetterQueryService {

    private final LetterRepository letterRepository;

    @Override
    public Letter getLetter(Long letterId) {
        return letterRepository.findById(letterId)
                .orElseThrow(() -> new LetterHandler(ErrorStatus.LETTER_NOT_FOUND));
    }
}
