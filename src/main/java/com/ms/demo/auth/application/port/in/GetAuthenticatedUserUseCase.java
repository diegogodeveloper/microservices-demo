package com.ms.demo.auth.application.port.in;

import com.ms.demo.auth.domain.model.User;

public interface GetAuthenticatedUserUseCase {
    User getAuthenticatedUser(String token);
}
