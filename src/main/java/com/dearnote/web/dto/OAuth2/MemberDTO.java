package com.dearnote.web.dto.OAuth2;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class MemberDTO {

    @Getter
    @Setter
    @Builder
    public static class JoinDTO{
        String name;
        @Email
        String email;
        String username;
        String role;
    }



}
