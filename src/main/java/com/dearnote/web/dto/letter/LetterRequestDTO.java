package com.dearnote.web.dto.letter;

import com.dearnote.domain.enums.Font;
import com.dearnote.domain.enums.LetterPaper;
import com.dearnote.domain.enums.Wax;
import com.dearnote.validation.annotation.ExistMember;
import jakarta.validation.constraints.Email;
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

        // 이미지 추가 예정
    }

    @Getter
    @Setter
    public static class ReceiverRequestDTO{  // 수신자 지정 요청 DTO
        @NotBlank(message = "수신자 이메일은 필수입니다.")
        @Email(message = "유효한 이메일 주소여야 합니다.")
        private String receiverEmail;
    }
}
