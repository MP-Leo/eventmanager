package com.frg.eventmanager.adapter.repository.reactive;

import com.frg.eventmanager.adapter.repository.entity.EventEntity;
import com.frg.eventmanager.domain.enums.EventStatus;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReactiveEventRepository extends ReactiveCrudRepository<EventEntity, UUID> {

    Flux<EventEntity> findAllByStatus(EventStatus status);

    Mono<EventEntity> findByTitle(String title);

}
