package com.frg.eventmanager.application.service;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.adapter.controller.dto.request.EventRequest;
import com.frg.eventmanager.application.port.in.EventUseCase;
import com.frg.eventmanager.domain.entity.Event;
import com.frg.eventmanager.domain.exception.ErrorMessages;
import com.frg.eventmanager.domain.exception.ResourceNotFoundException;
import com.frg.eventmanager.domain.repository.EventRepository;
import com.frg.eventmanager.infrastructure.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService implements EventUseCase {

    private final EventRepository eventRepository;

    @Override
    public Mono<Event> getEvent(String title) {
        return eventRepository.findByTitle(title)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(ErrorMessages.EVENT_NOT_FOUND)));
    }

    @Override
    public Flux<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Mono<Event> createEvent(EventRequest request, UserDTO user) {
        var event = EventMapper.requestToEntity(request, user);
        return eventRepository.save(event);
    }

    @Override
    public Mono<Event> finishEvent(UUID id) {
        return eventRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(ErrorMessages.EVENT_NOT_FOUND)))
                .flatMap(event -> {
                    event.finishEvent();
                    return eventRepository.save(event);
                });
    }

    @Override
    @Transactional
    public Mono<Event> cancelEvent(UUID id) {
        return eventRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(ErrorMessages.EVENT_NOT_FOUND)))
                .flatMap(event -> {
                    event.cancelEvent();
                    return eventRepository.save(event);
                });
    }
}
