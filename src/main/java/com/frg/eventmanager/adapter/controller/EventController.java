package com.frg.eventmanager.adapter.controller;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.adapter.controller.dto.request.EventRequest;
import com.frg.eventmanager.adapter.controller.dto.response.EventResponse;
import com.frg.eventmanager.application.port.in.EventUseCase;
import com.frg.eventmanager.infrastructure.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventUseCase eventUseCase;

    @GetMapping
    public Flux<EventResponse> getEvents() {
        return eventUseCase.getAllEvents()
                .map(EventMapper::entityToResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Mono<EventResponse> createEvent(@RequestBody EventRequest request, @AuthenticationPrincipal UserDTO user) {
        return eventUseCase.createEvent(request, user)
                .map(EventMapper::entityToResponse);
    }

}
