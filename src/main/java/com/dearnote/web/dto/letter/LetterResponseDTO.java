package com.dearnote.web.dto.letter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LetterResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LetterPaperResponseDTO {

        private String name;
        private String description;
    }
}
