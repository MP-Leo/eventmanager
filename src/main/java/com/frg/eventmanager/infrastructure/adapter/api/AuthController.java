package com.frg.eventmanager.infrastructure.adapter.api;

import com.frg.eventmanager.application.port.in.AuthUseCase;
import com.frg.eventmanager.infrastructure.adapter.auth.request.RegisterRequest;
import com.frg.eventmanager.infrastructure.adapter.auth.request.LoginRequest;
import com.frg.eventmanager.infrastructure.adapter.auth.request.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthUseCase authUseCase;

    @PostMapping("/register")
    public Mono<ResponseEntity<Void>> register(@RequestBody @Valid RegisterRequest registerRequest) {

        return authUseCase.register(registerRequest.username(), registerRequest.email(), registerRequest.password())
                .thenReturn(ResponseEntity.ok().build());
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<TokenResponse>> login(@RequestBody @Valid LoginRequest loginRequest) {

        return authUseCase.login(loginRequest.username(), loginRequest.password())
                .map(TokenResponse::new)
                .map(ResponseEntity::ok);
    }

}
