package com.frg.eventmanager.infrastructure.exception.handler;

import com.frg.eventmanager.domain.exception.ProblemDetails;
import com.frg.eventmanager.domain.exception.ResourceAlreadyExistException;
import com.frg.eventmanager.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ResponseEntity<ProblemDetails>> handleNotFound(ResourceNotFoundException ex, ServerWebExchange exchange) {

        ProblemDetails problem = ProblemDetails.builder()
                .type(URI.create("https://api.eventmanager.com/errors/not-found"))
                .title("Resource not found")
                .status(404)
                .detail(ex.getMessage())
                .instance(URI.create(String.valueOf(exchange.getRequest().getURI())))
                .build();

        return Mono.just(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem));
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public Mono<ResponseEntity<ProblemDetails>> handleNotFound(ResourceAlreadyExistException ex, ServerWebExchange exchange) {

        ProblemDetails problem = ProblemDetails.builder()
                .type(URI.create("https://api.eventmanager.com/errors/already-exists"))
                .title("Resource already exists")
                .status(400)
                .detail(ex.getMessage())
                .instance(URI.create(String.valueOf(exchange.getRequest().getURI())))
                .build();

        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem));
    }
}
