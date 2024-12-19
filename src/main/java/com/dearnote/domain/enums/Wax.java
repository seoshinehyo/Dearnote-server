package com.dearnote.domain.enums;

import com.dearnote.web.dto.letter.LetterResponseDTO;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Wax {
    RED, YELLOW, GREEN, BLUE, BLACK;

    // 이 과정을 converter로 뺄 수 있으나, 프로젝트 볼륨이 크지 않아 편의상 enum 타입에서 DTO로 변환
    private LetterResponseDTO.WaxResponseDTO toWaxDTO() {
        return LetterResponseDTO.WaxResponseDTO.builder()
                .name(this.name())
                .build();
    }

    // 이 과정을 converter로 뺄 수 있으나, 프로젝트 볼륨이 크지 않아 편의상 enum 타입에서 DTO로 변환
    public static LetterResponseDTO.WaxResponseDTOList toWaxDTOList() {
        List<LetterResponseDTO.WaxResponseDTO> waxList = Arrays.stream(values())
                .map(Wax::toWaxDTO)
                .collect(Collectors.toList());

        return LetterResponseDTO.WaxResponseDTOList.builder()
                .waxList(waxList)
                .build();
    }
}
