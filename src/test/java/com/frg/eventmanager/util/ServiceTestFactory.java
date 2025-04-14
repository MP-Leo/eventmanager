package com.frg.eventmanager.util;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.adapter.controller.dto.request.EventRequest;
import com.frg.eventmanager.domain.entity.Event;
import com.frg.eventmanager.domain.entity.Subscription;
import com.frg.eventmanager.domain.enums.EventStatus;
import com.frg.eventmanager.domain.enums.SubscriptionStatus;
import com.frg.eventmanager.domain.enums.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public class ServiceTestFactory {

    public static UserDTO createDefaultUser() {
        return UserDTO.builder()
                .id(UUID.randomUUID())
                .email("steve@email.com")
                .name("Steve St")
                .role(UserRole.ADMIN)
                .build();
    }

    public static Event createEvent(UserDTO creator) {
        return new Event(
                UUID.randomUUID(),
                "Technology Event",
                "Random technology event",
                LocalDateTime.now().plusMonths(3),
                LocalDateTime.now(),
                EventStatus.ACTIVE,
                50,
                creator.id(),
                creator.name());
    }


    public static EventRequest createEventRequest() {
        return EventRequest.builder()
                .title("Technology Event")
                .description("Random technology event")
                .eventDate(LocalDateTime.now().plusMonths(3))
                .capacity(50)
                .build();
    }

    public static Subscription createSubscription(UserDTO creator, Event event) {
        return new Subscription(
                UUID.randomUUID(),
                creator.id(),
                event.getId(),
                java.time.LocalDateTime.now(),
                SubscriptionStatus.CONFIRMED);
    }

}