package com.frg.eventmanager.adapter.controller.dto.response;

import com.frg.eventmanager.domain.enums.EventStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventResponse(
        String title,
        String description,
        LocalDateTime eventDate,
        LocalDateTime createdAt,
        EventStatus status,
        int capacity) {
}
