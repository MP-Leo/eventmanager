package com.frg.eventmanager.adapter.controller.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public record TokenResponse(String accessToken, LocalDateTime expiresAt) implements Serializable {
}
