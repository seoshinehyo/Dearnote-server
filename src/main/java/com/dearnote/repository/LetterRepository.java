package com.dearnote.repository;

import com.dearnote.domain.Letter;
import com.dearnote.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {

    Page<Letter> findAllBySender(Member member, Pageable pageable);
    Page<Letter> findAllByReceiver(Member member, Pageable pageable);
}