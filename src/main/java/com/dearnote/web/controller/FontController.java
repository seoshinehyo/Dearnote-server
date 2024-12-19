package com.dearnote.web.controller;

import com.dearnote.service.FontService;
import com.dearnote.web.dto.FontResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dearnote")
@RequiredArgsConstructor
public class FontController {

    private final FontService fontService;

    @Operation(summary = "폰트 목록 조회", description = "사용 가능한 폰트 목록을 반환합니다.")
    @GetMapping("/font")
    public ResponseEntity<FontResponseDTO> getFonts() {
        FontResponseDTO response = fontService.getAllFonts();
        return ResponseEntity.ok(response);
    }
}
