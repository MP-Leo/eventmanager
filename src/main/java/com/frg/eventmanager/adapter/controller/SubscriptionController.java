package com.frg.eventmanager.adapter.controller;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.application.port.in.SubscriptionUseCase;
import com.frg.eventmanager.domain.entity.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionUseCase subscriptionUseCase;

    @PostMapping("/{eventId}")
    public Mono<Subscription> subscribe(@PathVariable UUID eventId, @AuthenticationPrincipal UserDTO user) {
        return subscriptionUseCase.subscribe(eventId, user);
    }

    @DeleteMapping("/{eventId}")
    public Mono<Void> deleteSubscription(@PathVariable UUID eventId, @AuthenticationPrincipal UserDTO user) {
        return subscriptionUseCase.deleteSubscription(eventId, user);
    }

    @GetMapping
    public Flux<Subscription> getAllSubscriptions(@AuthenticationPrincipal UserDTO user) {
        return subscriptionUseCase.getSubscriptionsByUser(user);
    }

}
