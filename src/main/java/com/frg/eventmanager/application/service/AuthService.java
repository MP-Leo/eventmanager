package com.frg.eventmanager.application.service;

import com.frg.eventmanager.application.port.in.AuthUseCase;
import com.frg.eventmanager.domain.repository.UserRepository;
import com.frg.eventmanager.adapter.controller.dto.response.TokenResponse;
import com.frg.eventmanager.infrastructure.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<TokenResponse> login(String email, String password) {
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)))
                .flatMap(user -> {

                    if (!passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED));
                    }

                    var token = jwtUtil.generateToken(user);
                    return Mono.just(token);
                });
    }

}
