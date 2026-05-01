package com.ms.demo.auth.infrastructure.adapter.in.rest.dto;

public record LoginRequest(
        String username,
        String password
) {
}
