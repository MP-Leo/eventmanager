package com.frg.eventmanager.application.service;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.application.port.in.SubscriptionUseCase;
import com.frg.eventmanager.domain.entity.Subscription;
import com.frg.eventmanager.domain.enums.SubscriptionStatus;
import com.frg.eventmanager.domain.exception.ErrorMessages;
import com.frg.eventmanager.domain.exception.ResourceAlreadyExistException;
import com.frg.eventmanager.domain.exception.ResourceNotFoundException;
import com.frg.eventmanager.domain.repository.EventRepository;
import com.frg.eventmanager.domain.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService implements SubscriptionUseCase {

    private final SubscriptionRepository subscriptionRepository;

    private final EventRepository eventRepository;

    @Override
    public Mono<Subscription> subscribe(UUID eventId, UserDTO userDTO) {

        return eventRepository.findById(eventId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(ErrorMessages.EVENT_NOT_FOUND)))
                .flatMap(event ->
                        subscriptionRepository.existsByUserIdAndEventId(userDTO.id(), eventId)
                                .flatMap(alreadySubscribed -> {
                                    if (alreadySubscribed) {
                                        return Mono.error(new ResourceAlreadyExistException(ErrorMessages.SUBSCRIPTION_ALREADY_EXISTS));
                                    }

                                    var subscription = generateNewSubscription(userDTO.id(), eventId);

                                    return subscriptionRepository.save(subscription);
                                })
                );
    }

    @Override
    public Mono<Void> deleteSubscription(UUID eventId, UserDTO userDTO) {
        return eventRepository.findById(eventId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(ErrorMessages.EVENT_NOT_FOUND)))
                .flatMap(event ->
                        subscriptionRepository.findByUserIdAndEventId(userDTO.id(), eventId)
                                .switchIfEmpty(Mono.error(new ResourceAlreadyExistException(ErrorMessages.SUBSCRIPTION_NOT_FOUND)))
                                .flatMap(subscription -> subscriptionRepository.deleteById(subscription.getId()))
                );
    }

    @Override
    public Flux<Subscription> getSubscriptionsByUser(UserDTO userDTO) {
        return subscriptionRepository.findAllByUserId(userDTO.id());
    }

    private Subscription generateNewSubscription(UUID userId, UUID eventId) {
        return new Subscription(null, userId, eventId, LocalDateTime.now(), SubscriptionStatus.CONFIRMED);
    }

}
