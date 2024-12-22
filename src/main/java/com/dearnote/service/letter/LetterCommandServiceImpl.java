package com.dearnote.service.letter;

import com.dearnote.domain.Letter;
import com.dearnote.domain.enums.LetterStatus;
import com.dearnote.repository.LetterRepository;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class LetterCommandServiceImpl implements LetterCommandService {

    private final LetterRepository letterRepository;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    @Transactional
    public Letter sendLetter(Letter letter) {
        Letter savedLetter = letterRepository.save(letter);

        // 3시간 뒤에 상태를 UNLOCKED로 변경
        scheduleUnlock(savedLetter);

        return savedLetter;
    }

    @Override
    public void scheduleUnlock(Letter letter) {
        scheduler.schedule(() -> {
            try {
                // UNLOCKED로 상태 변경
                letter.setStatus(LetterStatus.UNLOCKED);
                updateLetterStatus(letter);
            } catch (Exception e) {
                System.err.println("편지 상태 변경 중 오류 발생: " + e.getMessage());
            }
        }, 3, TimeUnit.HOURS);
    }

    @Override
    @Transactional
    public void updateLetterStatus(Letter letter) {
        letterRepository.save(letter); // 변경된 상태를 DB에 저장
    }

    @PreDestroy
    public void shutdownScheduler() {
        scheduler.shutdown(); // 앱 종료시 스케줄러 종료
    }
}
