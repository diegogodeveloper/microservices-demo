package com.ms.demo.auth.infrastructure.adapter.in.rest;

import com.ms.demo.auth.application.port.in.GetAuthenticatedUserUseCase;
import com.ms.demo.auth.application.port.in.LoginUseCase;
import com.ms.demo.auth.domain.model.Credentials;
import com.ms.demo.auth.domain.model.User;
import com.ms.demo.auth.infrastructure.adapter.in.rest.dto.LoginRequest;
import com.ms.demo.auth.infrastructure.adapter.in.rest.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(
        name = "Autenticación",
        description = "Endpoints relacionados con autenticación y generación de tokens."
)
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final GetAuthenticatedUserUseCase getAuthenticatedUserUseCase;

    public AuthController(LoginUseCase loginUseCase, GetAuthenticatedUserUseCase getAuthenticatedUserUseCase) {
        this.loginUseCase = loginUseCase;
        this.getAuthenticatedUserUseCase = getAuthenticatedUserUseCase;
    }


    @PostMapping("/login")
    @Operation(summary = "API de Autenticación", description = "Valida las credenciales del usuario y genera un token de autenticación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        var token = loginUseCase.login(
                new Credentials(request.username(), request.password())
        );

        return new LoginResponse(token.value());
    }

    @GetMapping("/me")
    public User getAuthenticatedUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return getAuthenticatedUserUseCase.getAuthenticatedUser(authHeader);
    }


}
