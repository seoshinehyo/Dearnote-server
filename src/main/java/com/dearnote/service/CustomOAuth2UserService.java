package com.dearnote.service;

import com.dearnote.domain.Member;
import com.dearnote.repository.MemberRepository;
import com.dearnote.service.member.MemberQueryService;
import com.dearnote.web.dto.OAuth2.CustomOAuth2User;
import com.dearnote.web.dto.OAuth2.MemberDTO;
import com.dearnote.web.dto.OAuth2.NaverResponse;
import com.dearnote.web.dto.OAuth2.OAuth2response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final MemberQueryService memberService; // MemberService 의존성 주입


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // OAuth2User 정보를 기본적으로 가져옴
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        // 로그인한 서비스의 등록 ID
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2response oAuth2Response = null;

        // 각 서비스에 맞는 response 객체 생성 (예: Naver)
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }

        // 사용자명 생성
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        // 회원이 이미 존재하는지 확인
        Member existMember = memberRepository.findByUsername(username).orElse(null);

        if (existMember == null) {
            // 기존에 없는 사용자라면 새로운 회원 등록
            MemberDTO.JoinDTO joinDTO = MemberDTO.JoinDTO.builder()
                    .name(oAuth2Response.getName())
                    .email(oAuth2Response.getEmail())
                    .username(username)
                    .role("ROLE_USER")  // 기본 역할 설정
                    .build();

            // MemberService에서 회원가입 처리
            Member newMember = memberService.registerMember(joinDTO);

            return new CustomOAuth2User(joinDTO);
        } else {
            // 기존 회원이라면 정보 업데이트
            existMember.updateEmailAndName(oAuth2Response.getEmail(), oAuth2Response.getName());
            memberRepository.save(existMember);

            MemberDTO.JoinDTO joinDTO = MemberDTO.JoinDTO.builder()
                    .name(existMember.getName())
                    .email(existMember.getEmail())
                    .username(existMember.getUsername())
                    .role(existMember.getRole())
                    .build();

            return new CustomOAuth2User(joinDTO);
        }
    }
}
