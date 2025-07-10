package xyz.juniorapeles.catalog.exception;

import org.springframework.http.HttpStatus;

public record ApiError(
        HttpStatus status,
        String message,
        String debugMessage
) {}
