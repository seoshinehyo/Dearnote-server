package com.dearnote.service.letter;

import com.dearnote.domain.Letter;

public interface LetterCommandService {

    Letter sendLetter(Letter letter);
}
