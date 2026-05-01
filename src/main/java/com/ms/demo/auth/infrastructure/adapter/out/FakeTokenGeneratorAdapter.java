package com.ms.demo.auth.infrastructure.adapter.out;

import com.ms.demo.auth.application.port.out.TokenGeneratorPort;
import com.ms.demo.auth.domain.model.AuthToken;
import com.ms.demo.auth.domain.model.Credentials;
import org.springframework.stereotype.Component;

@Component
public class FakeTokenGeneratorAdapter implements TokenGeneratorPort {
    @Override
    public AuthToken generate(Credentials credentials) {
        return new AuthToken("fake-jwt-token");
    }
}
