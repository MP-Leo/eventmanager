package com.frg.eventmanager.infrastructure.adapter.auth.request;

import java.io.Serializable;
import java.time.LocalDateTime;

public record TokenResponse(String accessToken, LocalDateTime expiresAt) implements Serializable {
}
