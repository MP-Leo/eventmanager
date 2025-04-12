package com.frg.eventmanager.adapter.repository;

import com.frg.eventmanager.adapter.repository.entity.EventEntity;
import com.frg.eventmanager.adapter.repository.reactive.ReactiveEventRepository;
import com.frg.eventmanager.domain.entity.Event;
import com.frg.eventmanager.domain.enums.EventStatus;
import com.frg.eventmanager.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventRepositoryAdapter implements EventRepository {

    private final ReactiveEventRepository reactiveEventRepository;

    @Override
    public Mono<Event> findById(UUID id) {
        return reactiveEventRepository.findById(id)
                .map(EventEntity::toDomain);
    }

    @Override
    public Mono<Event> findByTitle(String title) {
        return reactiveEventRepository.findByTitle(title)
                .mapNotNull(EventEntity::toDomain);
    }

    @Override
    public Flux<Event> findAllActive() {
        return reactiveEventRepository.findAllByStatus(EventStatus.ACTIVE)
                .map(EventEntity::toDomain);
    }

    @Override
    public Flux<Event> findAll() {
        return reactiveEventRepository.findAll()
                .map(EventEntity::toDomain);
    }

    @Override
    public Mono<Event> save(Event event) {
        return reactiveEventRepository.save(EventEntity.fromDomain(event))
                .map(EventEntity::toDomain);

    }

    @Override
    public Mono<Void> delete(UUID id) {
        return reactiveEventRepository.deleteById(id);
    }
}
