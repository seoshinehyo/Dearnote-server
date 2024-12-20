package com.dearnote.service.letter;

import com.dearnote.domain.Letter;
import com.dearnote.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LetterCommandServiceImpl implements LetterCommandService {

    private final LetterRepository letterRepository;

    @Override
    @Transactional
    public Letter sendLetter(Letter letter) {
        return letterRepository.save(letter);
    }
}
