package com.frg.eventmanager.application.service;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.domain.entity.Event;
import com.frg.eventmanager.domain.entity.Subscription;
import com.frg.eventmanager.domain.exception.ErrorMessages;
import com.frg.eventmanager.domain.exception.ResourceAlreadyExistException;
import com.frg.eventmanager.domain.exception.ResourceNotFoundException;
import com.frg.eventmanager.domain.repository.EventRepository;
import com.frg.eventmanager.domain.repository.SubscriptionRepository;
import com.frg.eventmanager.util.ServiceTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private EventRepository eventRepository;
    @Mock
    private SubscriptionRepository subscriptionRepository;

    private SubscriptionService service;

    private UserDTO userDTO;
    private Event event;
    private UUID eventId;
    private Subscription subscription;

    @BeforeEach
    void setUp() {
        service = new SubscriptionService(subscriptionRepository, eventRepository);

        this.userDTO = ServiceTestFactory.createDefaultUser();
        this.event = ServiceTestFactory.createEvent(userDTO);
        this.eventId = event.getId();
        this.subscription = ServiceTestFactory.createSubscription(userDTO, event);
    }

    @Test
    @DisplayName("[SUCCESS] Should subscribe user")
    void shouldSubscribeUserToEventSuccessfully() {

        when(eventRepository.findById(any(UUID.class))).thenReturn(Mono.just(event));
        when(subscriptionRepository.existsByUserIdAndEventId(userDTO.id(), eventId)).thenReturn(Mono.just(false));
        when(subscriptionRepository.save(any())).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(service.subscribe(eventId, userDTO))
                .expectNextMatches(sub -> sub.getEventId().equals(eventId) && sub.getUserId().equals(userDTO.id()))
                .verifyComplete();
    }

    @Test
    @DisplayName("[FAILURE] Should fail with event not found")
    void shouldFailIfEventNotFound() {

        when(eventRepository.findById(eventId)).thenReturn(Mono.empty());

        StepVerifier.create(service.subscribe(eventId, userDTO))
                .expectErrorMatches(error ->
                        error instanceof ResourceNotFoundException &&
                                error.getMessage().equals(ErrorMessages.EVENT_NOT_FOUND)
                )
                .verify();
    }

    @Test
    @DisplayName("[SUCCESS] Should delete subscription")
    void shouldDeleteSubscriptionSuccessfully() {

        when(eventRepository.findById(eventId)).thenReturn(Mono.just(event));
        when(subscriptionRepository.findByUserIdAndEventId(userDTO.id(), eventId)).thenReturn(Mono.just(subscription));
        when(subscriptionRepository.deleteById(subscription.getId())).thenReturn(Mono.empty());

        StepVerifier.create(service.deleteSubscription(eventId, userDTO))
                .verifyComplete();
    }

    @Test
    @DisplayName("[FAILURE] Should fail with event not found")
    void shouldFailToDeleteIfEventNotFound() {
        when(eventRepository.findById(eventId)).thenReturn(Mono.empty());

        StepVerifier.create(service.deleteSubscription(eventId, userDTO))
                .expectError(ResourceNotFoundException.class)
                .verify();
    }

    @Test
    @DisplayName("[FAILURE] Should fail with subscription not found")
    void shouldFailToDeleteIfSubscriptionNotFound() {

        when(eventRepository.findById(eventId)).thenReturn(Mono.just(event));
        when(subscriptionRepository.findByUserIdAndEventId(userDTO.id(), eventId)).thenReturn(Mono.empty());

        StepVerifier.create(service.deleteSubscription(eventId, userDTO))
                .expectErrorMatches(error ->
                        error instanceof ResourceAlreadyExistException &&
                                error.getMessage().equals(ErrorMessages.SUBSCRIPTION_NOT_FOUND)
                )
                .verify();
    }

    @Test
    @DisplayName("[SUCCESS] Should return all user's subscriptions")
    void shouldReturnAllSubscriptionsByUser() {
        Subscription sub1 = ServiceTestFactory.createSubscription(userDTO, event);
        Subscription sub2 = ServiceTestFactory.createSubscription(userDTO, event);

        when(subscriptionRepository.findAllByUserId(userDTO.id())).thenReturn(Flux.just(sub1, sub2));

        StepVerifier.create(service.getSubscriptionsByUser(userDTO))
                .expectNext(sub1)
                .expectNext(sub2)
                .verifyComplete();
    }

}