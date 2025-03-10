package com.example.ws.application.usecases;

import com.example.ws.domain.entities.UserDomain;
import com.example.ws.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindUserByIdUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FindUserByIdUseCase findUserByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handle_ShouldReturnUser_WhenUserExists() {
        // Arrange
        Long userId = 1L;
        UserDomain mockUser = new UserDomain.UserBuilder()
                .id(userId)
                .name("John Doe")
                .email("john@example.com")
                .build();

        when(userRepository.findById(userId)).thenReturn(mockUser);

        // Act
        UserDomain result = findUserByIdUseCase.handle(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void handle_ShouldReturnNull_WhenUserDoesNotExist() {
        // Arrange
        Long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(null);

        // Act
        UserDomain result = findUserByIdUseCase.handle(userId);

        // Assert
        assertNull(result);

        verify(userRepository, times(1)).findById(userId);
    }
}
