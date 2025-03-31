package com.frg.eventmanager.infrastructure.adapter.repository;

import com.frg.eventmanager.infrastructure.adapter.repository.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReactiveUserRepository extends ReactiveCrudRepository<UserEntity, UUID> {

    Mono<UserEntity> findByEmail(String email);

    Mono<Boolean> existsByEmail(String email);
}
