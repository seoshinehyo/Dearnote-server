package com.dearnote.domain.enums;

import com.dearnote.web.dto.letter.LetterResponseDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LetterPaper {
    TYPE1("차분하고 깔끔한 무지 종이."),
    TYPE2("심플하고 깨끗한 무지 종이."),
    TYPE3("부드러운 질감이 느껴지는 베이지 톤의 종이."),
    TYPE4("차분한 그리드 패턴이 돋보이는 종이.");

    private final String description;

    LetterPaper(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static List<LetterResponseDTO.LetterPaperResponseDTO> toResponseDTOList() {
        return Arrays.stream(values())
                .map(letterPaper -> new LetterResponseDTO.LetterPaperResponseDTO(
                        letterPaper.name(),
                        letterPaper.description
                ))
                .collect(Collectors.toList());
    }
}
