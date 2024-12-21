package com.dearnote.web.dto.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ImageResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegistImageResponseDTO {

        private String originFileName;
        private String storeFileUrl;
        private String storeFileName;
        private Integer size;
        private Long letterId;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetImageResponseDTO {

        private String originFileName;
        private String storeFileUrl;
        private String storeFileName;
        private Integer size;
        private Long letterId;
    }
}
