package com.frg.eventmanager.domain.repository;

import com.frg.eventmanager.domain.entity.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EventRepository {

    Mono<Event> findById(UUID id);

    Flux<Event> findAllActive();

    Flux<Event> findAll();

    Mono<Event> save(Event event);

    Mono<Void> delete(UUID id);
}
