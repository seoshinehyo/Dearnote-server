package com.dearnote.web.dto.letter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

public class LetterResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LetterPaperResponseDTO {

        private String name;
        private String description;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LetterPaperResponseDTOList {

        private List<LetterResponseDTO.LetterPaperResponseDTO> letterPaperList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WaxResponseDTO {
        private String name;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WaxResponseDTOList {
        private List<LetterResponseDTO.WaxResponseDTO> waxList;
    }
}
