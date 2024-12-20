package com.dearnote.repository;

import com.dearnote.domain.LetterBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LetterBoxRepository extends JpaRepository<LetterBox, Long> {

    Optional<LetterBox> findByMemberId(Long memberId);
}
