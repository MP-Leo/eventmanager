package com.frg.eventmanager.application.service;

import com.frg.eventmanager.application.port.in.AuthUseCase;
import com.frg.eventmanager.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserRepository userRepository;

    @Override
    public Mono<Void> register(String name, String email, String password) {
        //TODO
        return null;
    }

    @Override
    public Mono<String> login(String name, String password) {
        //TODO
        return null;
    }
}
