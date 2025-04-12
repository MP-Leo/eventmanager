package com.frg.eventmanager.adapter.repository;

import com.frg.eventmanager.adapter.repository.entity.SubscriptionEntity;
import com.frg.eventmanager.adapter.repository.reactive.ReactiveSubscriptionRepository;
import com.frg.eventmanager.domain.entity.Subscription;
import com.frg.eventmanager.domain.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SubscriptionRepositoryAdapter implements SubscriptionRepository {

    private final ReactiveSubscriptionRepository reactiveSubscriptionRepository;

    @Override
    public Mono<Subscription> save(Subscription subscription) {
        return reactiveSubscriptionRepository.save(SubscriptionEntity.fromDomain(subscription))
                .map(SubscriptionEntity::toDomain);
    }

    @Override
    public Flux<Subscription> findAllByUserId(UUID userId) {
        return reactiveSubscriptionRepository.findAllByUserId(userId)
                .map(SubscriptionEntity::toDomain);
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return reactiveSubscriptionRepository.deleteById(id);
    }

    @Override
    public Mono<Subscription> findByUserIdAndEventId(UUID userId, UUID eventId) {
        return reactiveSubscriptionRepository.findByUserIdAndEventId(userId, eventId)
                .mapNotNull(SubscriptionEntity::toDomain);
    }

    @Override
    public Mono<Boolean> existsByUserIdAndEventId(UUID userId, UUID eventId) {
        return reactiveSubscriptionRepository.existsByUserIdAndEventId(userId, eventId);
    }

}
