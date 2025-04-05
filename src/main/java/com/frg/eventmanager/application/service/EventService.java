package com.frg.eventmanager.application.service;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.adapter.controller.dto.request.EventRequest;
import com.frg.eventmanager.application.port.in.EventUseCase;
import com.frg.eventmanager.domain.entity.Event;
import com.frg.eventmanager.domain.repository.EventRepository;
import com.frg.eventmanager.infrastructure.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EventService implements EventUseCase {

    private final EventRepository eventRepository;

    @Override
    public Mono<Event> getEvent(String id) {
        return null;
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
    public Mono<Event> updateEvent(EventRequest request) {
        return null;
    }

    @Override
    public Mono<Event> deleteEvent(String id) {
        return null;
    }
}
