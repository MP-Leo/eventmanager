package com.frg.eventmanager.application.port.in;

import com.frg.eventmanager.infrastructure.adapter.auth.request.TokenResponse;
import reactor.core.publisher.Mono;

public interface AuthUseCase {

    Mono<TokenResponse> login(String name, String password);

}
