package com.frg.eventmanager.adapter.repository.reactive;

import com.frg.eventmanager.adapter.repository.entity.SubscriptionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReactiveSubscriptionRepository extends ReactiveCrudRepository<SubscriptionEntity, UUID> {

    Mono<Boolean> existsByUserIdAndEventId(UUID userId, UUID eventId);

    Mono<SubscriptionEntity> findByUserIdAndEventId(UUID userId, UUID eventId);

    Flux<SubscriptionEntity> findAllByUserId(UUID userId);
}
