package com.ms.demo.auth.application.service;


import com.ms.demo.auth.application.port.out.TokenGeneratorPort;
import com.ms.demo.auth.domain.exception.InvalidCredentialsException;
import com.ms.demo.auth.domain.model.AuthToken;
import com.ms.demo.auth.domain.model.Credentials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LoginServiceTest {

    @Test
    void loginFailed() {
        TokenGeneratorPort tokenGeneratorPort = mock(TokenGeneratorPort.class);
        LoginService loginService = new LoginService(tokenGeneratorPort);

        Credentials credentials = new Credentials("useer", "wrong_password");

        assertThrows(
                InvalidCredentialsException.class,
                () -> loginService.login(credentials)
        );

        verify(tokenGeneratorPort, never()).generate(credentials);
    }

    @Test
    void loginSuccessful() {
        TokenGeneratorPort tokenGeneratorPort = mock(TokenGeneratorPort.class);
        LoginService loginService = new LoginService(tokenGeneratorPort);
        Credentials credentials = new Credentials("user", "1234");
        AuthToken expectedToken = new AuthToken("jwt-token");

        when(tokenGeneratorPort.generate(credentials))
                .thenReturn(expectedToken);

        AuthToken actualToken = loginService.login(credentials);

        assertEquals(expectedToken, actualToken);
        verify(tokenGeneratorPort).generate(credentials);
    }
}
