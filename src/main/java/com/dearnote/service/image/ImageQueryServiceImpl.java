package com.dearnote.service.image;

import com.dearnote.domain.Image;
import com.dearnote.domain.Letter;
import com.dearnote.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageQueryServiceImpl implements ImageQueryService {

    private final ImageRepository imageRepository;

    @Override
    public Image getImage(Letter letter) {
        return imageRepository.findByLetter(letter);
    }
}
