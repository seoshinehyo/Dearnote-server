package com.dearnote.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Getter
public class Jwt {

    private final String header;
    private final String issuer;
    private final String clientSecret;
    private final int expirySeconds;
    private final Algorithm algorithm;

    public Jwt(
            @Value("${jwt.token.header}") String header,
            @Value("${jwt.token.issuer}") String issuer,
            @Value("${jwt.token.clientSecret}") String clientSecret,
            @Value("${jwt.token.expirySeconds}") int expirySeconds
    ) {
        this.header = header;
        this.issuer = issuer;
        this.clientSecret = clientSecret;
        this.expirySeconds = expirySeconds;
        this.algorithm = Algorithm.HMAC512(clientSecret);
    }

    public String newToken(Long memberId, String name, String email, String[] roles) {
        try {
            Date now = new Date();
            Date expiry = new Date(now.getTime() + expirySeconds * 1000L);

            // JWT 생성
            String token = JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(now)
                    .withExpiresAt(expiry)
                    .withClaim("memberId", memberId)
                    .withClaim("name", name)
                    .withClaim("email", email)
                    .withArrayClaim("roles", roles)
                    .sign(algorithm);

            System.out.println("Generated JWT: " + token); // 디버깅용 로그
            return token;
        } catch (Exception e) {
            System.err.println("JWT 생성 실패: " + e.getMessage()); // 예외 로그
            throw new RuntimeException("JWT 생성 실패", e);
        }

    }
}