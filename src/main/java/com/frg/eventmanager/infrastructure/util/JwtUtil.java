package com.frg.eventmanager.infrastructure.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.frg.eventmanager.domain.entity.User;
import com.frg.eventmanager.adapter.controller.dto.response.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMillis;

    public TokenResponse generateToken(User user) {
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(expirationMillis);

        Algorithm algorithm = Algorithm.HMAC256(secret);

        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("name", user.getName())
                .withClaim("role", user.getRole().name())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiry))
                .sign(algorithm);

        return new TokenResponse(token, LocalDateTime.ofInstant(expiry, ZoneOffset.UTC));
    }

    public ReactiveJwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), Algorithm.HMAC256(secret).getName());
        return NimbusReactiveJwtDecoder.withSecretKey(secretKey).build();
    }
}
