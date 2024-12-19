package com.dearnote.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FontResponseDTO {
    private List<String> font;
}
