package com.scott.userservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException e) {
        RestException exception = new RestException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")),
                e
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        RestException exception = new RestException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")),
                e
        );
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException e) throws IOException {
        RestException exception = new RestException(
                e.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")),
                e
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}