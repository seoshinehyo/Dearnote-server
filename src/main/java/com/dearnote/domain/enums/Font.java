package com.dearnote.domain.enums;

import com.dearnote.web.dto.letter.LetterResponseDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Font {
    RIDIBatang, Handletting1, Handletting2, Handletting3, Handletting4, Handletting5;

    public static LetterResponseDTO.FontResponseDTOList toFontDTOList() {
        List<LetterResponseDTO.FontResponseDTO> fontList = Arrays.stream(values())
                .map(Font::toFontDTO)
                .collect(Collectors.toList());

        return LetterResponseDTO.FontResponseDTOList.builder()
                .fontList(fontList)
                .build();
    }

    private LetterResponseDTO.FontResponseDTO toFontDTO() {
        return LetterResponseDTO.FontResponseDTO.builder()
                .name(this.name())
                .build();
    }


}
