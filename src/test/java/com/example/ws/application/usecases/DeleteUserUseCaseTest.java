package com.example.ws.application.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteUserUseCaseTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private DeleteUserUseCase deleteUserUseCase;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void execute_ShouldReturnTrue_WhenUserIsDeletedSuccessfully() {
//        // Arrange
//        Long userId = 1L;
//        when(userRepository.delete(userId)).thenReturn(true);
//
//        // Act
//        Boolean result = deleteUserUseCase.execute(userId);
//
//        // Assert
//        assertTrue(result);
//        verify(userRepository, times(1)).delete(userId);
//    }
//
//    @Test
//    void execute_ShouldReturnFalse_WhenUserDeletionFails() {
//        // Arrange
//        Long userId = 2L;
//        when(userRepository.delete(userId)).thenReturn(false);
//
//        // Act
//        Boolean result = deleteUserUseCase.execute(userId);
//
//        // Assert
//        assertFalse(result);
//        verify(userRepository, times(1)).delete(userId);
//    }
}
