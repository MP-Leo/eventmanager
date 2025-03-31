package com.frg.eventmanager.infrastructure.adapter.auth.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String username, @NotBlank String password) {
}
