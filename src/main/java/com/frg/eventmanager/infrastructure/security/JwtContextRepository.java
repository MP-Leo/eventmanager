package com.frg.eventmanager.infrastructure.security;

import com.frg.eventmanager.domain.enums.UserRole;
import com.frg.eventmanager.adapter.controller.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
public class JwtContextRepository implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {

        var role = UserRole.valueOf(jwt.getClaim("role"));

        var userDTO = UserDTO.builder()
                .id(UUID.fromString(jwt.getSubject()))
                .name(jwt.getClaim("name"))
                .email(jwt.getClaim("email"))
                .role(role)
                .build();

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );

        return Mono.just(new UsernamePasswordAuthenticationToken(userDTO, jwt, authorities));
    }
}
