package com.dearnote.web.dto.letter;

import com.dearnote.domain.enums.Font;
import com.dearnote.domain.enums.LetterPaper;
import com.dearnote.domain.enums.Wax;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    public static class LetterPaperResponseDTOList { // 편지지 조회 응답 DTO

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
    public static class WaxResponseDTOList { // 실링 왁스 조회 응답 DTO
        private List<LetterResponseDTO.WaxResponseDTO> waxList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SendLetterResponseDTO { // 편지 전송 응답 DTO
        private Long letterId;
        private Long senderId;
        private Long receiverId;
        private LetterPaper letterPaper;
        private Font font;
        private Wax wax;
        private Long keywordId;
        private String content;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RandomKeywordResponseDTO{ // 키워드 랜덤 받아오기 DTO
        private Long keywordId;
        private String keyword;
        private String sentence;
    }
}
