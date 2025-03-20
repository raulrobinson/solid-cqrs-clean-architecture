package com.example.ws.infrastructure.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserCommandControllerTest {

//    @Mock
//    private CreateUserUseCase createUserUseCase;
//
//    @Mock
//    private DeleteUserUseCase deleteUserUseCase;
//
//    @Mock
//    private UpdateUserUseCase updateUserUseCase;
//
//    @InjectMocks
//    private UserCommandController userCommandController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void createUser_ShouldReturnUserApplication_WhenUserIsCreated() {
//        // Arrange
//        UserRequestDto requestDto = new UserRequestDto("John Doe", "john.doe@example.com");
//        UserDomain userDomain = new UserDomain.UserBuilder().name(requestDto.getName()).email(requestDto.getEmail()).build();
//        UserApplication userApplication = UserMapper.INSTANCE.domainToApplication(userDomain);
//
//        when(createUserUseCase.execute(requestDto)).thenReturn(userDomain);
//
//        // Act
//        ResponseEntity<?> response = userCommandController.createUser(requestDto);
//
//        // Assert
//        UserApplication responseBody = (UserApplication) response.getBody();
//        assertNotNull(responseBody);
//        assertEquals(userApplication.getName(), responseBody.getName());
//        assertEquals(userApplication.getEmail(), responseBody.getEmail());
//    }
//
//    @Test
//    void deleteUser_ShouldReturnTrue_WhenUserIsDeletedSuccessfully() {
//        // Arrange
//        Long userId = 1L;
//        when(deleteUserUseCase.execute(userId)).thenReturn(true);
//
//        // Act
//        ResponseEntity<?> response = userCommandController.deleteUser(userId);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(ResponseEntity.ok(true), response);
//        verify(deleteUserUseCase, times(1)).execute(userId);
//    }
//
//    @Test
//    void updateUser_ShouldReturnUpdatedUserApplication_WhenUserIsUpdated() {
//        // Arrange
//        Long userId = 1L;
//        UserRequestDto requestDto = new UserRequestDto("Updated Name", "updated.email@example.com");
//        UserDomain updatedUserDomain = new UserDomain.UserBuilder().id(userId).name(requestDto.getName()).email(requestDto.getEmail()).build();
//        UserApplication updatedUserApplication = UserMapper.INSTANCE.domainToApplication(updatedUserDomain);
//
//        when(updateUserUseCase.execute(userId, requestDto)).thenReturn(updatedUserDomain);
//
//        // Act
//        ResponseEntity<?> response = userCommandController.updateUser(userId, requestDto);
//
//        // Assert
//        UserApplication responseBody = (UserApplication) response.getBody();
//        assertNotNull(responseBody);
//        assertEquals(updatedUserApplication.getName(), responseBody.getName());
//        assertEquals(updatedUserApplication.getEmail(), responseBody.getEmail());
//    }
}
