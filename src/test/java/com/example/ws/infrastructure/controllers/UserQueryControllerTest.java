package com.example.ws.infrastructure.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserQueryControllerTest {

//    @Mock
//    private GetAllUsersUseCase getAllUsersUseCase;
//
//    @Mock
//    private FindUserByIdUseCase findUserByIdUseCase;
//
//    @InjectMocks
//    private UserQueryController userQueryController;
//
//    private UserDomain userDomain;
//    private UserApplication userApplication;
//
//    @BeforeEach
//    void setUp() {
//        userDomain = new UserDomain(1L, "John Doe", "john.doe@example.com");
//        userApplication = new UserApplication(1L, "John Doe", "john.doe@example.com");
//    }
//
//    @Test
//    void findAllUsers_ShouldReturnListOfUsers() {
//        List<UserDomain> users = Arrays.asList(userDomain);
//        when(getAllUsersUseCase.handle()).thenReturn(users);
//
//        ResponseEntity<?> response = userQueryController.findAllUsers();
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertNotNull(response.getBody());
//        verify(getAllUsersUseCase, times(1)).handle();
//    }
//
//    @Test
//    void getUserById_ShouldReturnUser_WhenUserExists() {
//        Long userId = 1L;
//        when(findUserByIdUseCase.handle(userId)).thenReturn(userDomain);
//
//        ResponseEntity<?> response = userQueryController.getUserById(userId);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertNotNull(response.getBody());
//        verify(findUserByIdUseCase, times(1)).handle(userId);
//    }
}
