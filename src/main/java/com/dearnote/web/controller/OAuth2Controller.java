package com.dearnote.web.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class OAuth2Controller {

    // 로그인 성공 시 리다이렉트
    @GetMapping("/login/success")
    public String loginSuccess(OAuth2AuthenticationToken authentication) {
        OAuth2User principal = authentication.getPrincipal();
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");

        // 로그인 후 메인 페이지로 리다이렉트
        return "redirect:/home";  // /home 페이지로 리다이렉트
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        // 이미 로그인한 상태라면 /home 페이지로 리다이렉트
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home";  // 이미 로그인된 경우 /home으로 리다이렉트
        }
        return "login";  // 로그인 페이지로 리턴
    }

    // 메인 페이지
    @GetMapping("/home")
    public String homePage() {
        return "home";  // 메인 페이지로 리턴
    }

}
