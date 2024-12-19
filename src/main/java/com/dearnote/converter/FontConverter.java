package com.dearnote.converter;

import com.dearnote.domain.enums.Font;
import com.dearnote.web.dto.FontResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FontConverter {

    public FontResponseDTO toFontResponseDTO() {
        // Enum에서 데이터를 가져와 변환
        List<String> fonts = Stream.of(Font.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return new FontResponseDTO(fonts);
    }
}
