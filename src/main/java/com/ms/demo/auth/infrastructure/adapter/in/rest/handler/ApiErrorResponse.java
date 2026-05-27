package com.ms.demo.auth.infrastructure.adapter.in.rest.handler;

import java.time.LocalDateTime;
import java.util.List;

public record ApiErrorResponse(
        int status,
        String message,
        List<String> errors,
        LocalDateTime timestamp
) {}
