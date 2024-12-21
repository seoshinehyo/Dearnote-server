package com.dearnote.web.dto.letter;

import com.dearnote.domain.enums.Font;
import com.dearnote.domain.enums.LetterPaper;
import com.dearnote.domain.enums.Wax;
import com.dearnote.validation.annotation.ExistMember;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class LetterRequestDTO {

    @Getter
    @Setter
    public static class SendLetterRequestDTO { // 편지 전송 요청 DTO

        @ExistMember
        Long senderId;

        @ExistMember
        Long receiverId;

        @NotNull
        LetterPaper letterPaper;

        @NotNull
        Font font;

        @NotNull
        Long keywordId;

        @NotNull
        Wax wax;

        @NotBlank
        String content;

        String imageDescription;
    }
}
