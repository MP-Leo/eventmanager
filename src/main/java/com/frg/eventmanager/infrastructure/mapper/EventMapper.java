package com.frg.eventmanager.infrastructure.mapper;

import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import com.frg.eventmanager.adapter.controller.dto.request.EventRequest;
import com.frg.eventmanager.adapter.controller.dto.response.EventResponse;
import com.frg.eventmanager.domain.entity.Event;
import com.frg.eventmanager.domain.enums.EventStatus;

import java.time.LocalDateTime;

public final class EventMapper {

    private EventMapper() {}

    public static EventResponse entityToResponse(Event event) {
        return EventResponse.builder()
                .title(event.getTitle())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .createdAt(event.getCreatedAt())
                .status(event.getStatus())
                .capacity(event.getCapacity())
                .build();
    }

    public static Event requestToEntity(EventRequest eventRequest, UserDTO userDTO) {
        return new Event(
                null,
                eventRequest.title(),
                eventRequest.description(),
                eventRequest.eventDate(),
                LocalDateTime.now(),
                EventStatus.ACTIVE,
                eventRequest.capacity(),
                userDTO.id(),
                userDTO.name());
    }

}
