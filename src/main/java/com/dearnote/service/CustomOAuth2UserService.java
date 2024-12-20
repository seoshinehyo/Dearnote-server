package com.dearnote.service;

import com.dearnote.converter.MemberConverter;
import com.dearnote.domain.Member;
import com.dearnote.repository.LetterBoxRepository;
import com.dearnote.repository.MemberRepository;
import com.dearnote.web.dto.OAuth2.CustomOAuth2User;
import com.dearnote.web.dto.OAuth2.NaverResponse;
import com.dearnote.web.dto.OAuth2.OAuth2response;
import com.dearnote.web.dto.OAuth2.MemberDTO;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    public CustomOAuth2UserService(MemberRepository memberRepository, LetterBoxRepository letterBoxRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }

        // Create username using provider and providerId
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        // Find member by username, throw exception if not found
        Member existMember = memberRepository.findByUsername(username)
                .orElse(null);  // If not found, return null

        if (existMember == null) {
            // Create a new Member using the DTO and Converter
            MemberDTO.JoinDTO joinDTO = MemberDTO.JoinDTO.builder()
                    .name(oAuth2Response.getName())
                    .email(oAuth2Response.getEmail())
                    .username(username)
                    .role("ROLE_USER") // Corrected role value format
                    .build();

            Member newMember = MemberConverter.toMember(joinDTO);
            memberRepository.save(newMember);

            return new CustomOAuth2User(joinDTO);
        } else {
            // Update existing Member
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

