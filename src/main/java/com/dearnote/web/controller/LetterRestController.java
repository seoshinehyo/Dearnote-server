package com.dearnote.web.controller;

import com.dearnote.apipayload.ApiResponse;
import com.dearnote.converter.LetterConverter;
import com.dearnote.domain.Keyword;
import com.dearnote.domain.Letter;
import com.dearnote.domain.LetterBox;
import com.dearnote.domain.Member;
import com.dearnote.domain.enums.LetterPaper;
import com.dearnote.domain.enums.Wax;
import com.dearnote.service.keyword.KeywordQueryService;
import com.dearnote.service.letter.LetterCommandService;
import com.dearnote.service.letterbox.LetterBoxQueryService;
import com.dearnote.service.member.MemberQueryService;
import com.dearnote.validation.annotation.ExistKeyword;
import com.dearnote.validation.annotation.ExistMember;
import com.dearnote.web.dto.letter.LetterRequestDTO;
import com.dearnote.web.dto.letter.LetterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/dearnote")
@Validated
public class LetterRestController {

    private final MemberQueryService memberQueryService;
    private final LetterCommandService letterCommandService;
    private final KeywordQueryService keywordQueryService;
    private final LetterBoxQueryService letterBoxCommandService;

    @GetMapping("/letterPaper")
    @Operation(summary = "사용 가능 편지지 조회 api", description = "사용 가능한 편지지를 조회하는 api입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    public ApiResponse<LetterResponseDTO.LetterPaperResponseDTOList> getLetterPaperList() {

        return ApiResponse.onSuccess(LetterPaper.toLetterPaperDTOList());
    }

    @GetMapping("/wax")
    @Operation(summary = "실링 왁스 조회 api", description = "실링왁스를 조회하는 api입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    public ApiResponse<LetterResponseDTO.WaxResponseDTOList> getWaxList() {

        return ApiResponse.onSuccess(Wax.toWaxDTOList());
    }

    @PostMapping("/letters")
    @Operation(summary = "편지 전송 api", description = "편지를 전송하는 api입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    public ApiResponse<LetterResponseDTO.SendLetterResponseDTO> send(@RequestBody @Valid LetterRequestDTO.SendLetterRequestDTO request){

        Member sender = memberQueryService.getMember(request.getSenderId());
        Member receiver = memberQueryService.getMember(request.getReceiverId());
        Keyword keyword = keywordQueryService.getKeyword(request.getKeywordId());

        LetterBox letterBox = letterBoxCommandService.getLetterBox(request.getSenderId());

        Letter sendLetter = LetterConverter.toSendLetter(request, sender, receiver, keyword, letterBox);

        Letter letter = letterCommandService.sendLetter(sendLetter);

        return ApiResponse.onSuccess(LetterConverter.toSendLetterResultDTO(letter));
    }

    @GetMapping("/letter/random")
    @Operation(summary = "키워드 랜덤 받아오기 api", description = "랜덤한 키워드를 받아오는 api입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<LetterResponseDTO.RandomKeywordResponseDTO> getRandomKeyword() {
        Keyword randomKeyword = keywordQueryService.getRandomKeyword();

        LetterResponseDTO.RandomKeywordResponseDTO responseDTO = LetterConverter.toRandomKeywordResponseDTO(randomKeyword);

        return ApiResponse.onSuccess(responseDTO);
    }
}
