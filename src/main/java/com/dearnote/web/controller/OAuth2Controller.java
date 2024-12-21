package com.dearnote.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class OAuth2Controller {


    @GetMapping("/logout-success")
    @Operation(summary = "로그아웃 성공", description = "로그아웃이 성공적으로 이루어진 후 반환되는 메시지를 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그아웃 성공 메시지")
    })
    public String logoutSuccessPage() {
        return "로그아웃 되었습니다. 다시 로그인해 주세요.";
    }



    @Operation(summary = "로그인 상태 확인", description = "현재 로그인 상태를 확인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 상태 확인 성공")
    })
    @GetMapping("/api/auth/status")
    public Map<String, Object> checkLoginStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> response = new HashMap<>();

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
            response.put("loggedIn", true);
            response.put("username", authentication.getName());
        } else {
            response.put("loggedIn", false);
        }

        return response;
    }

}
