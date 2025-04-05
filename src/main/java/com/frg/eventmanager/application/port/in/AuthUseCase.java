package com.frg.eventmanager.application.port.in;

import com.frg.eventmanager.adapter.controller.dto.response.TokenResponse;
import reactor.core.publisher.Mono;

public interface AuthUseCase {

    Mono<TokenResponse> login(String name, String password);

}
