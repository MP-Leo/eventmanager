package com.frg.eventmanager.domain.exception;

import lombok.Builder;

import java.net.URI;

@Builder
public record ProblemDetails(
        URI type,
        String title,
        int status,
        String detail,
        URI instance
) {}
