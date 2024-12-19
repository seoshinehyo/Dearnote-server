package com.dearnote.service;

import com.dearnote.converter.FontConverter;
import com.dearnote.web.dto.FontResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FontService {

    private final FontConverter fontConverter;

    public FontResponseDTO getAllFonts() {
        return fontConverter.toFontResponseDTO();
    }
}
