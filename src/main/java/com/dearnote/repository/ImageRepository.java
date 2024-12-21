package com.dearnote.repository;

import com.dearnote.domain.Image;
import com.dearnote.domain.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByLetter(Letter letter);
}
