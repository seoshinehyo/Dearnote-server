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


    // 이 과정을 converter로 뺄 수 있으나, 프로젝트 볼륨이 크지 않아 편의상 enum 타입에서 DTO로 변환
    private LetterResponseDTO.LetterPaperResponseDTO toLetterPaperDTO() {
        return LetterResponseDTO.LetterPaperResponseDTO.builder()
                .name(this.name())
                .description(this.description)
                .build();
    }

    // 이 과정을 converter로 뺄 수 있으나, 프로젝트 볼륨이 크지 않아 편의상 enum 타입에서 DTO로 변환
    public static LetterResponseDTO.LetterPaperResponseDTOList toLetterPaperDTOList() {

        List<LetterResponseDTO.LetterPaperResponseDTO> letterPaperList = Arrays.stream(values())
                .map(LetterPaper::toLetterPaperDTO)
                .collect(Collectors.toList());

        return LetterResponseDTO.LetterPaperResponseDTOList.builder()
                .letterPaperList(letterPaperList)
                .build();
    }
}
