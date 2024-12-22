package com.dearnote.service.letter;

import com.dearnote.domain.Letter;

public interface LetterCommandService {

    Letter sendLetter(Letter letter);

    void scheduleUnlock(Letter letter);

    void updateLetterStatus(Letter letter);
}
