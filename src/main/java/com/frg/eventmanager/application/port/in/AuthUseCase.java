package com.frg.eventmanager.application.port.in;

import reactor.core.publisher.Mono;

public interface AuthUseCase {

    Mono<Void> register(String name, String email, String password);

    Mono<String> login(String name, String password);

}
