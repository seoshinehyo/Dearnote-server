package com.dearnote.service.image;

import com.dearnote.apipayload.code.status.ErrorStatus;
import com.dearnote.apipayload.exception.handler.LetterHandler;
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
    public Image getImageByLetter(Letter letter) {
        return imageRepository.findByLetter(letter);
    }

    @Override
    public Image getImage(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new LetterHandler(ErrorStatus.IMAGE_NOT_FOUND));
    }
}
