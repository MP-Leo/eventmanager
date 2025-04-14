package com.frg.eventmanager.adapter.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Builder
public record EventRequest(
        @NotBlank String title,
        String description,
        @NotNull LocalDateTime eventDate,
        @RequestParam(defaultValue = "20") Integer capacity) {
}
