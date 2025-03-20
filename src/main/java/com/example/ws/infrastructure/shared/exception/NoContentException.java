package com.example.ws.infrastructure.shared.exception;

public class NoContentException extends RuntimeException {

    public NoContentException(String message) {
        super(message);
    }
}