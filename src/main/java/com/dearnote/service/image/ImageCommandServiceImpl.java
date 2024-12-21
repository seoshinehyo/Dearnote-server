package com.dearnote.service.image;

import com.dearnote.domain.Image;
import com.dearnote.domain.Letter;
import com.dearnote.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ImageCommandServiceImpl implements ImageCommandService {

    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Image saveImage(String originFileName, Integer size, String storeFileName, String storeFileUrl, Letter letter) {
        Image image = Image.builder()
                .originFileName(originFileName)
                .size(size)
                .storeFileName(storeFileName)
                .storeFileUrl(storeFileUrl)
                .letter(letter)
                .build();

        return imageRepository.save(image); // 저장된 Image 엔티티 반환
    }
}
