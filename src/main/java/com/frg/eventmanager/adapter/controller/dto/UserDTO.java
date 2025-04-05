package com.frg.eventmanager.adapter.controller.dto;

import com.frg.eventmanager.domain.enums.UserRole;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDTO(UUID id, String email, String name, UserRole role) {
}
