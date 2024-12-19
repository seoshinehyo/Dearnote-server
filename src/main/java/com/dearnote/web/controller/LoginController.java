package com.dearnote.web.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    // OAuth2AuthorizedClientService 주입
    public LoginController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/")
    public String home() {
        return "<html><body>"
                + "<h1>Hello!</h1>"
                + "<a href=\"/oauth2/authorization/naver\">네이버 로그인</a>"
                + "</body></html>";
    }

    @GetMapping("/login/success")
    public Map<String, Object> loginSuccess(OAuth2AuthenticationToken authentication) {
        // 클라이언트 가져오기
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName()
        );

        // 액세스 토큰 및 리프레시 토큰 가져오기
        String accessToken = client.getAccessToken().getTokenValue();
        String refreshToken = client.getRefreshToken() != null ? client.getRefreshToken().getTokenValue() : null;

        // 사용자 정보 가져오기
        OAuth2User user = authentication.getPrincipal();

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("name", user.getAttribute("name"));
        response.put("email", user.getAttribute("email"));
        response.put("access_token", accessToken);
        response.put("refresh_token", refreshToken);

        return response;
    }


}
