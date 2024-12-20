package com.dearnote.web.dto;

import com.dearnote.domain.enums.LetterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LetterPreviewListDTO{
        List<LetterPreviewDTO> letterList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LetterPreviewDTO{
        Long letterId;
        Long senderId;
        Long receiverId;
        String name;
        LetterType type;
        Boolean mark;
        Boolean isPublic;
        String keyword;
        LocalDateTime sentAt;
    }
}
