package com.dearnote.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Jwt jwt;

    public JwtAuthenticationFilter(Jwt jwt) {
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = resolveToken(request);

        if (token != null) {
            try {
                DecodedJWT decoded = verifyToken(token);

                Long memberId = decoded.getClaim("memberId").asLong();
                String name = decoded.getClaim("name").asString();
                String email = decoded.getClaim("email").asString();

                // SecurityContext에 인증 정보 설정
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // HttpServletRequest 속성 저장 (선택 사항)
                request.setAttribute("memberId", memberId);
                request.setAttribute("email", email);
                request.setAttribute("name", name);

            } catch (JWTVerificationException e) {
                SecurityContextHolder.clearContext(); // 인증 실패 시 초기화
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired token");
                return; // 요청 처리 중단
            }
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwt.getHeader());
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    private DecodedJWT verifyToken(String token) throws JWTVerificationException {
        return JWT.require(jwt.getAlgorithm())
                .withIssuer(jwt.getIssuer())
                .build()
                .verify(token);
    }
}
