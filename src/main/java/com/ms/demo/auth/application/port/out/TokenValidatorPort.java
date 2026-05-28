package com.ms.demo.auth.application.port.out;

public interface TokenValidatorPort {
    String validateAndGetSubject(String token);
}
