package com.sda.eventservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EventErrorResponse> handleException(EventNotFoundException exc) {

        EventErrorResponse error = new EventErrorResponse(HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EventErrorResponse> handleException(Exception exc) {

        EventErrorResponse error = new EventErrorResponse(HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
