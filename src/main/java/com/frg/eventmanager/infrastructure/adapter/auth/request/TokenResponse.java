package com.frg.eventmanager.infrastructure.adapter.auth.request;

import java.io.Serializable;

public record TokenResponse(String accessToken) implements Serializable {
}
