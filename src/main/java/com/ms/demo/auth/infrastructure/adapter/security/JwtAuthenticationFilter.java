package com.ms.demo.auth.infrastructure.adapter.security;

import com.ms.demo.auth.application.port.out.TokenValidatorPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenValidatorPort tokenValidatorPort;

    public JwtAuthenticationFilter(TokenValidatorPort tokenValidatorPort) {
        this.tokenValidatorPort = tokenValidatorPort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken  = request.getHeader("Authorization");
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = bearerToken.substring(7);
        String userName = tokenValidatorPort.validateAndGetSubject(token);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userName,
                        null,
                        Collections.emptyList()
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
