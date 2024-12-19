package com.dearnote.web.controller;

import com.dearnote.apipayload.ApiResponse;
import com.dearnote.domain.enums.LetterPaper;
import com.dearnote.web.dto.letter.LetterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/dearnote")
@Validated
public class LetterRestController {

    @GetMapping("/letterPaper")
    @Operation(summary = "사용 가능 편지지 조회", description = "사용 가능한 편지지를 조회하는 api입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<List<LetterResponseDTO.LetterPaperResponseDTO>> getLetterPaperList() {
        List<LetterResponseDTO.LetterPaperResponseDTO> letterPapers = LetterPaper.toResponseDTOList();

        return ApiResponse.onSuccess(letterPapers);
    }
}
