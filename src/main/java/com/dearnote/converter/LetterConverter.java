package com.dearnote.converter;

import com.dearnote.domain.Keyword;
import com.dearnote.domain.Letter;
import com.dearnote.domain.LetterBox;
import com.dearnote.domain.Member;
import com.dearnote.domain.enums.*;
import com.dearnote.web.dto.letter.LetterRequestDTO;
import com.dearnote.web.dto.letter.LetterResponseDTO;

public class LetterConverter {

    public static Letter toSendLetter(LetterRequestDTO.SendLetterRequestDTO request, Member sender,
                                      Member receiver, Keyword keyword, LetterBox letterBox) { // 편지 전송 요청

        return Letter.builder()
                .sender(sender)
                .receiver(receiver)
                .letterBox(letterBox)
                .keyword(keyword)
                .content(request.getContent())
                .imageDescription(request.getImageDescription())
                .type(LetterType.SENT)
                .status(LetterStatus.LOCKED)
                .mark(false)
                .isPublic(false)
                .font(request.getFont())
                .letterPaper(request.getLetterPaper())
                .wax(request.getWax())
                .build();
    }

    public static LetterResponseDTO.SendLetterResponseDTO toSendLetterResultDTO(Letter letter) { // 편지 전송 응답

        return LetterResponseDTO.SendLetterResponseDTO.builder()
                .letterId(letter.getId())
                .senderId(letter.getSender().getId())
                .receiverId(letter.getReceiver().getId())
                .letterPaper(letter.getLetterPaper())
                .font(letter.getFont())
                .wax(letter.getWax())
                .keywordId(letter.getKeyword().getId())
                .content(letter.getContent())
                // 이미지 추가 예정
                .createdAt(letter.getCreatedAt())
                .build();
    }

    public static LetterResponseDTO.RandomKeywordResponseDTO toRandomKeywordResponseDTO(Keyword keyword){  // 랜덤한 키워드 응답
        return LetterResponseDTO.RandomKeywordResponseDTO.builder()
                .keywordId(keyword.getId())
                .keyword(keyword.getKeyword())
                .sentence(keyword.getSentence())
                .build();
    }
}
