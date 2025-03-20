package com.example.ws.infrastructure.shared.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

//    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();
//    private final HttpServletRequest request = mock(HttpServletRequest.class);


//    @Test
//    void handleUserNotFoundException() {
//        when(request.getRequestURI()).thenReturn("/users/1");
//
//        UserNotFoundException ex = new UserNotFoundException("User not found");
//        ResponseEntity<Map<String, Object>> response = handler.handleUserNotFoundException(ex, request);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//
//        Map<String, Object> body = response.getBody();
//        assertNotNull(body);
//        assertEquals(404, body.get("status"));
//        assertEquals("User Not Found", body.get("error"));
//        assertEquals("/users/1", body.get("path"));
//        assertEquals("User not found", body.get("message"));
//    }

//    @Test
//    void handleCreateUserException() {
//        when(request.getRequestURI()).thenReturn("/users");
//
//        CreateUserException ex = new CreateUserException("User already exists");
//        //ResponseEntity<Map<String, Object>> response = handler.handleCreateUserException(ex, request);
//        ResponseEntity<ExceptionResponse> response = handler.handleCreateUserException(ex, request);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
////        Map<String, Object> body = response.getBody();
//        ExceptionResponse body = response.getBody();
//        assertNotNull(body);
//        assertEquals(400, body.getStatus());
//        assertEquals("Warning creating user", body.getError());
//        assertEquals("/users", body.getPath());
//        assertEquals("User already exists", body.getMessage());
//    }

//    @Test
//    void handleUpdateUserException() {
//        when(request.getRequestURI()).thenReturn("/users/1");
//
//        UpdateUserException ex = new UpdateUserException("User not updated");
//        ResponseEntity<Map<String, Object>> response = handler.handleUpdateUserException(ex, request);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//        Map<String, Object> body = response.getBody();
//        assertNotNull(body);
//        assertEquals(400, body.get("status"));
//        assertEquals("Warning updating user", body.get("error"));
//        assertEquals("/users/1", body.get("path"));
//        assertEquals("User not updated", body.get("message"));
//    }

//    @Test
//    void handleDeleteUserException() {
//        when(request.getRequestURI()).thenReturn("/users/1");
//
//        DeleteUserException ex = new DeleteUserException("User not deleted");
//        ResponseEntity<Map<String, Object>> response = handler.handleDeleteUserException(ex, request);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//
//        Map<String, Object> body = response.getBody();
//        assertNotNull(body);
//        assertEquals(400, body.get("status"));
//        assertEquals("User Not Deleted", body.get("error"));
//        assertEquals("/users/1", body.get("path"));
//        assertEquals("User not deleted", body.get("message"));
//    }

//    @Test
//    void handleGlobalException() {
//        when(request.getRequestURI()).thenReturn("/users");
//
//        Exception ex = new Exception("Unexpected error");
//        ResponseEntity<Map<String, Object>> response = handler.handleGlobalException(ex, request);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//
//        Map<String, Object> body = response.getBody();
//        assertNotNull(body);
//        assertEquals(500, body.get("status"));
//        assertEquals("Internal Server Error", body.get("error"));
//        assertEquals("/users", body.get("path"));
//        assertEquals("Unexpected error", body.get("message"));
//    }
}
