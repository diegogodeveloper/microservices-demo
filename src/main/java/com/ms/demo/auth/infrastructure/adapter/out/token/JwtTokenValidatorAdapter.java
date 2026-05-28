package com.ms.demo.auth.infrastructure.adapter.out.token;

import com.ms.demo.auth.application.port.out.TokenValidatorPort;
import com.ms.demo.auth.domain.exception.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenValidatorAdapter implements TokenValidatorPort {

    private final String secret;

    public JwtTokenValidatorAdapter(@Value("${security.jwt.secret}") String secret) {
        this.secret = secret;
    }

    @Override
    public String validateAndGetSubject(String token) {
        try{
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            var claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException("Token expired.");
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException("Token invalid.");
        }
    }
}
