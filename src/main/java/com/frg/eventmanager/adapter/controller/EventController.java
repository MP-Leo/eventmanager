package com.frg.eventmanager.adapter.controller;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.adapter.controller.dto.request.EventRequest;
import com.frg.eventmanager.application.port.in.EventUseCase;
import com.frg.eventmanager.domain.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventUseCase eventUseCase;

    @GetMapping
    public Flux<Event> getEvents() {
        return eventUseCase.getAllEvents();
    }

    @GetMapping("/{title}")
    public Mono<Event> getEventByTitle(@PathVariable String title) {
        return eventUseCase.getEvent(title);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Mono<Event> createEvent(@RequestBody EventRequest request, @AuthenticationPrincipal UserDTO user) {
        return eventUseCase.createEvent(request, user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/finish")
    public Mono<Event> finishEvent(@PathVariable UUID id) {
        return eventUseCase.finishEvent(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/cancel")
    public Mono<Event> cancelEvent(@PathVariable UUID id) {
        return eventUseCase.cancelEvent(id);
    }

}
