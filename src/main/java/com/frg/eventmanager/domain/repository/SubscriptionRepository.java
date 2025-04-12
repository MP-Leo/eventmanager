package com.frg.eventmanager.domain.repository;

import com.frg.eventmanager.domain.entity.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SubscriptionRepository {

    Mono<Subscription> save(Subscription subscription);

    Flux<Subscription> findAllByUserId(UUID userId);

    Mono<Void> deleteById(UUID id);

    Mono<Boolean> existsByUserIdAndEventId(UUID userId, UUID eventId);

    Mono<Subscription> findByUserIdAndEventId(UUID userId, UUID eventId);

}
