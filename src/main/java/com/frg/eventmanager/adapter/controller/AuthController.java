package com.frg.eventmanager.adapter.controller;

import com.frg.eventmanager.application.port.in.AuthUseCase;
import com.frg.eventmanager.adapter.controller.dto.request.LoginRequest;
import com.frg.eventmanager.adapter.controller.dto.response.TokenResponse;
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

    @PostMapping("/login")
    public Mono<ResponseEntity<TokenResponse>> login(@RequestBody @Valid LoginRequest loginRequest) {

        return authUseCase.login(loginRequest.email(), loginRequest.password())
                .map(ResponseEntity::ok);
    }

}
