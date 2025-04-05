package com.frg.eventmanager.application.port.in;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.adapter.controller.dto.request.EventRequest;
import com.frg.eventmanager.domain.entity.Event;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventUseCase {

    Mono<Event> getEvent(String id);

    Flux<Event> getAllEvents();

    Mono<Event> createEvent(EventRequest request, UserDTO userDTO);

    Mono<Event> updateEvent(EventRequest request);

    Mono<Event> deleteEvent(String id);

}
