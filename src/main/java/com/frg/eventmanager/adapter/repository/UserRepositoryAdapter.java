package com.frg.eventmanager.adapter.repository;

import com.frg.eventmanager.adapter.repository.entity.UserEntity;
import com.frg.eventmanager.adapter.repository.reactive.ReactiveUserRepository;
import com.frg.eventmanager.domain.entity.User;
import com.frg.eventmanager.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final ReactiveUserRepository userRepository;

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserEntity::toDomain);
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(UserEntity.fromDomain(user))
                .map(UserEntity::toDomain);
    }

    @Override
    public Mono<User> findById(UUID id) {
        return userRepository.findById(id)
                .map(UserEntity::toDomain);
    }

}
