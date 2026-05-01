package com.ms.demo.auth.application.service;

import com.ms.demo.auth.application.port.in.LoginUseCase;
import com.ms.demo.auth.application.port.out.TokenGeneratorPort;
import com.ms.demo.auth.domain.exception.InvalidCredentialsException;
import com.ms.demo.auth.domain.model.AuthToken;
import com.ms.demo.auth.domain.model.Credentials;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUseCase {

    private final TokenGeneratorPort tokenGeneratorPort;

    public LoginService(TokenGeneratorPort tokenGeneratorPort) {
        this.tokenGeneratorPort = tokenGeneratorPort;
    }

    @Override
    public AuthToken login(Credentials credentials) {
        if (!"user".equals(credentials.username()) || !"1234".equals(credentials.password())) {
            throw new InvalidCredentialsException();
        }

        return tokenGeneratorPort.generate(credentials);
    }
}
