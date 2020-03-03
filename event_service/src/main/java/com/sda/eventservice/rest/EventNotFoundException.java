package com.sda.eventservice.rest;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException() {
        super();
    }

    public EventNotFoundException(String message) {
        super(message);
    }

    public EventNotFoundException(Throwable cause) {
        super(cause);
    }

    public EventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    protected EventNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
