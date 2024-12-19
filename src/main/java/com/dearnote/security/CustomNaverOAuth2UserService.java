package com.dearnote.security;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomNaverOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 네이버 응답 파싱
        Map<String, Object> attributes = oAuth2User.getAttributes();
        if (!attributes.containsKey("response")) {
            throw new OAuth2AuthenticationException("Response is missing in attributes: " + attributes);
        }

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        String email = (String) response.getOrDefault("email", "default@example.com"); // 기본값 추가
        String name = (String) response.getOrDefault("name", "Anonymous");

        // 사용자 정보 확인
        if (!attributes.containsKey("email")) {
            throw new IllegalArgumentException("Missing attribute 'email' in attributes");
        }

        System.out.println("OAuth2 Response: " + response); // 디버깅용 로그
        System.out.println("Parsed Response: email=" + email + ", name=" + name);

        return new DefaultOAuth2User(
                Collections.singletonList(() -> "ROLE_USER"),
                Map.of("name", name, "email", email),
                "email"
        );
    }

}