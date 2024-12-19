package com.dearnote.security;

import com.dearnote.domain.Member;

import com.dearnote.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final Jwt jwt;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String,Object> attributes = oAuth2User.getAttributes();
        Map<String,Object> responseMap = (Map<String,Object>) attributes.get("response");

        String email = (String) responseMap.get("email");
        String name = (String) responseMap.get("name");

        Member member = memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .name(name)
                                .email(email)
                                .letterBox(null) // 필요하다면 초기값 지정
                                .letter(null) // 필요하다면 초기값 지정
                                .build()
                ));
        System.out.println("Saved Member: " + member); // 디버깅용 로그

        String token = jwt.newToken(member.getId(), member.getName(), member.getEmail(), new String[]{"ROLE_USER"});

        response.addHeader("Authorization", "Bearer " + token);
        // 로그인 후 메인 페이지로 리다이렉트
        response.sendRedirect("/");
    }
}
