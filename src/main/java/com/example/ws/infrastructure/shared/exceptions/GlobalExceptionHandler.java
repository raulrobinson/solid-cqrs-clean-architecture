package com.example.ws.infrastructure.shared.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GlobalError> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(GlobalError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .errorReason(ex.getMessage())
                .path(request.getRequestURI())
                .message("User Not Found")
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CreateUserException.class)
    public ResponseEntity<GlobalError> handleCreateUserException(CreateUserException ex, HttpServletRequest request) {
        log.warn("Warning creating user: {}", ex.getMessage());
        return new ResponseEntity<>(GlobalError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errorReason(ex.getMessage())
                .path(request.getRequestURI())
                .message("Warning creating user")
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UpdateUserException.class)
    public ResponseEntity<GlobalError> handleUpdateUserException(UpdateUserException ex, HttpServletRequest request) {
        return new ResponseEntity<>(GlobalError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errorReason(ex.getMessage())
                .path(request.getRequestURI())
                .message("Warning updating user")
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeleteUserException.class)
    public ResponseEntity<GlobalError> handleDeleteUserException(DeleteUserException ex, HttpServletRequest request) {
        return new ResponseEntity<>(GlobalError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .errorReason(ex.getMessage())
                .path(request.getRequestURI())
                .message("User Not Deleted")
                .build(), HttpStatus.BAD_REQUEST);
    }
}
