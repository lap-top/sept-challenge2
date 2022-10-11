package com.scott.accountservice.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

    public class RestException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public String getMessage() {
        return message;
    }

    private Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public RestException(String message, HttpStatus httpStatus, ZonedDateTime timestamp, Throwable throwable) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
        this.throwable = throwable;
    }

    private final ZonedDateTime timestamp;
}
