package com.dearnote.converter;

import com.dearnote.domain.*;
import com.dearnote.web.dto.image.ImageResponseDTO;

public class ImageConverter {

    public static ImageResponseDTO.RegistImageResponseDTO toRegistImageDTO(Image image, Letter letter) { // 이미지 등록

        return ImageResponseDTO.RegistImageResponseDTO.builder()
                .originFileName(image.getOriginFileName())
                .storeFileUrl(image.getStoreFileUrl())
                .storeFileName(image.getStoreFileName())
                .size(image.getSize())
                .letterId(letter.getId())
                .build();
    }
}
