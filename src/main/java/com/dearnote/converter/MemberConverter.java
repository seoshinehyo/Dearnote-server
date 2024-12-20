package com.dearnote.converter;

import com.dearnote.domain.Letter;
import com.dearnote.web.dto.MemberResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.LetterPreviewDTO letterPreviewDTO(Letter letter){
        return MemberResponseDTO.LetterPreviewDTO.builder()
                .letterId(letter.getId())
                .senderId(letter.getSender().getId())
                .receiverId(letter.getReceiver().getId())
                .name(letter.getReceiver().getName())
                .type(letter.getType())
                .mark(letter.getMark())
                .isPublic(letter.getIsPublic())
                .keyword(letter.getKeyword().getKeyword())
                .sentAt(letter.getCreatedAt())
                .build();
    }


    public static MemberResponseDTO.LetterPreviewListDTO letterPreviewListDTO(Page<Letter> letterList){

        List<MemberResponseDTO.LetterPreviewDTO> letterPreViewDTOList = letterList.stream()
                .map(MemberConverter::letterPreviewDTO).collect(Collectors.toList());

        return MemberResponseDTO.LetterPreviewListDTO.builder()
                .isLast(letterList.isLast())
                .isFirst(letterList.isFirst())
                .totalPage(letterList.getTotalPages())
                .totalElements(letterList.getTotalElements())
                .listSize(letterPreViewDTOList.size())
                .letterList(letterPreViewDTOList)
                .build();
    }
}
