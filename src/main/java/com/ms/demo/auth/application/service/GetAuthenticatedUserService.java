package com.ms.demo.auth.application.service;

import com.ms.demo.auth.application.port.in.GetAuthenticatedUserUseCase;
import com.ms.demo.auth.application.port.out.TokenValidatorPort;
import com.ms.demo.auth.domain.exception.InvalidTokenException;
import com.ms.demo.auth.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class GetAuthenticatedUserService implements GetAuthenticatedUserUseCase {

    private final TokenValidatorPort tokenValidatorPort;

    public GetAuthenticatedUserService(TokenValidatorPort tokenValidatorPort) {
        this.tokenValidatorPort = tokenValidatorPort;
    }

    @Override
    public User getAuthenticatedUser(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new InvalidTokenException("Authorization header is missing or invalid");
        }
        String tokenClean = token.substring(7);
        return new User(tokenValidatorPort.validateAndGetSubject(tokenClean));
    }
}
