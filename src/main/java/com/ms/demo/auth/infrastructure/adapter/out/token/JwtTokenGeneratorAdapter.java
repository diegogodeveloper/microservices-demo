package com.ms.demo.auth.infrastructure.adapter.out.token;

import com.ms.demo.auth.application.port.out.TokenGeneratorPort;
import com.ms.demo.auth.domain.model.AuthToken;
import com.ms.demo.auth.domain.model.Credentials;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtTokenGeneratorAdapter implements TokenGeneratorPort {


    private final String secret;
    private final int timeExpiration;

    public JwtTokenGeneratorAdapter(@Value("${security.jwt.secret}") String secret,
                                    @Value("${security.jwt.expiration-minutes}") int timeExpiration) {
        this.secret = secret;
        this.timeExpiration = timeExpiration;
    }

    @Override
    public AuthToken generate(Credentials credentials) {
        Instant now = Instant.now();
        Instant expiration = now.plus(timeExpiration, ChronoUnit.MINUTES);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder()
                .subject(credentials.username())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(key)
                .compact();
        return new AuthToken(token);
    }
}
