package com.ms.demo.auth.application.port.out;

import com.ms.demo.auth.domain.model.AuthToken;
import com.ms.demo.auth.domain.model.Credentials;

public interface TokenGeneratorPort {
    AuthToken generate(Credentials credentials);
}
