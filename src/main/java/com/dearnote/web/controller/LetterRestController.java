package com.dearnote.web.controller;

import com.dearnote.apiPayload.ApiResponse;
import com.dearnote.domain.enums.LetterPaper;
import com.dearnote.domain.enums.Wax;
import com.dearnote.web.dto.letter.LetterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ApiResponse<LetterResponseDTO.LetterPaperResponseDTOList> getLetterPaperList() {

        return ApiResponse.onSuccess(LetterPaper.toLetterPaperDTOList());
    }

    @GetMapping("/wax")
    @Operation(summary = "실링 왁스 조회", description = "실링왁스를 조회하는 api입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<LetterResponseDTO.WaxResponseDTOList> getWaxList() {

        return ApiResponse.onSuccess(Wax.toWaxDTOList());
    }
}
