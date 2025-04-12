package com.frg.eventmanager.application.port.in;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.domain.entity.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SubscriptionUseCase {

    Mono<Subscription> subscribe(UUID eventId, UserDTO userDTO);

    Mono<Void> deleteSubscription(UUID eventId, UserDTO userDTO);

    Flux<Subscription> getSubscriptionsByUser(UserDTO userDTO);

}
