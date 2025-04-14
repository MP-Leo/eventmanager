package com.frg.eventmanager.application.service;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.adapter.controller.dto.request.EventRequest;
import com.frg.eventmanager.domain.entity.Event;
import com.frg.eventmanager.domain.exception.ErrorMessages;
import com.frg.eventmanager.domain.exception.ResourceNotFoundException;
import com.frg.eventmanager.domain.repository.EventRepository;
import com.frg.eventmanager.infrastructure.mapper.EventMapper;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    private EventService service;

    private UserDTO userDTO;
    private Event event;
    private UUID eventId;

    @BeforeEach
    void setUp() {
        service = new EventService(eventRepository);

        this.userDTO = ServiceTestFactory.createDefaultUser();
        this.event = ServiceTestFactory.createEvent(userDTO);
        this.eventId = event.getId();

    }

    @Test
    @DisplayName("[SUCCESS] Should return event")
    void shouldReturnEventByTitle() {
        when(eventRepository.findByTitle(anyString())).thenReturn(Mono.just(event));

        StepVerifier.create(service.getEvent(event.getTitle()))
                .expectNext(event)
                .verifyComplete();
    }

    @Test
    @DisplayName("[FAILURE] Should fail with title not found")
    void shouldFailWhenTitleNotFound() {
        when(eventRepository.findByTitle(anyString())).thenReturn(Mono.empty());

        StepVerifier.create(service.getEvent(event.getTitle()))
                .expectErrorMatches(error ->
                        error instanceof ResourceNotFoundException &&
                                error.getMessage().equals(ErrorMessages.EVENT_NOT_FOUND)
                )
                .verify();
    }

    @Test
    @DisplayName("[SUCCESS] Should return all events")
    void shouldReturnAllEvents() {
        Event event1 = ServiceTestFactory.createEvent(userDTO);
        Event event2 = ServiceTestFactory.createEvent(userDTO);

        when(eventRepository.findAll()).thenReturn(Flux.just(event1, event2));

        StepVerifier.create(service.getAllEvents())
                .expectNext(event1, event2)
                .verifyComplete();
    }

    @Test
    @DisplayName("[SUCCESS] Should create event")
    void shouldCreateEventSuccessfully() {
        EventRequest request = ServiceTestFactory.createEventRequest(userDTO);
        Event eventToSave = EventMapper.requestToEntity(request, userDTO);

        when(eventRepository.save(any())).thenReturn(Mono.just(eventToSave));

        StepVerifier.create(service.createEvent(request, userDTO))
                .expectNextMatches(saved -> saved.getTitle().equals(request.title()))
                .verifyComplete();
    }

    @Test
    @DisplayName("[SUCCESS] Should finish event")
    void shouldFinishEventSuccessfully() {
        when(eventRepository.findById(eventId)).thenReturn(Mono.just(event));
        when(eventRepository.save(event)).thenReturn(Mono.just(event));

        StepVerifier.create(service.finishEvent(eventId))
                .expectNext(event)
                .verifyComplete();
    }

    @Test
    @DisplayName("[Failure] Should fail event not found when finishing")
    void shouldFailToFinishIfNotFound() {
        when(eventRepository.findById(eventId)).thenReturn(Mono.empty());

        StepVerifier.create(service.finishEvent(eventId))
                .expectError(ResourceNotFoundException.class)
                .verify();
    }

    @Test
    @DisplayName("[SUCCESS] Should cancel with success")
    void shouldCancelEventSuccessfully() {
        when(eventRepository.findById(eventId)).thenReturn(Mono.just(event));
        when(eventRepository.save(event)).thenReturn(Mono.just(event));

        StepVerifier.create(service.cancelEvent(eventId))
                .expectNext(event)
                .verifyComplete();
    }

    @Test
    @DisplayName("[Failure] Should fail event not found when canceling")
    void shouldFailToCancelIfNotFound() {
        when(eventRepository.findById(eventId)).thenReturn(Mono.empty());

        StepVerifier.create(service.cancelEvent(eventId))
                .expectError(ResourceNotFoundException.class)
                .verify();
    }
}