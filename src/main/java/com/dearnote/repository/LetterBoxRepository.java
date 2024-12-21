package com.dearnote.repository;

import com.dearnote.domain.LetterBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface LetterBoxRepository extends JpaRepository<LetterBox, Long> {

    Optional<LetterBox> findByMemberId(Long memberId);

}
