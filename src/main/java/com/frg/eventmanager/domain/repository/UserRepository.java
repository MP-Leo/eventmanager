package com.frg.eventmanager.domain.repository;

import com.frg.eventmanager.domain.entity.User;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository {

    Mono<User> findByEmail(String email);

    Mono<Boolean> existsByEmail(String email);

    Mono<User> save(User user);

    Mono<User> findById(UUID id);

}
