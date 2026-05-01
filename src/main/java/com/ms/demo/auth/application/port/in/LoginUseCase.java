package com.ms.demo.auth.application.port.in;

import com.ms.demo.auth.domain.model.AuthToken;
import com.ms.demo.auth.domain.model.Credentials;

public interface LoginUseCase {
    AuthToken login(Credentials credentials);
}
