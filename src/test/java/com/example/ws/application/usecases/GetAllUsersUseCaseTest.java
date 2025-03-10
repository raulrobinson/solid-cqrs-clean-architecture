package com.example.ws.application.usecases;

import com.example.ws.domain.entities.UserDomain;
import com.example.ws.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAllUsersUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetAllUsersUseCase getAllUsersUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handle_ShouldReturnListOfUsers() {
        // Arrange
        List<UserDomain> mockUsers = Arrays.asList(
                new UserDomain.UserBuilder().id(1L).name("User1").email("user1@example.com").build(),
                new UserDomain.UserBuilder().id(2L).name("User2").email("user2@example.com").build()
        );

        when(userRepository.findAll()).thenReturn(mockUsers);

        // Act
        List<UserDomain> result = getAllUsersUseCase.handle();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("User1", result.get(0).getName());
        assertEquals("user2@example.com", result.get(1).getEmail());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void handle_ShouldReturnEmptyListWhenNoUsers() {
        // Arrange
        when(userRepository.findAll()).thenReturn(List.of());

        // Act
        List<UserDomain> result = getAllUsersUseCase.handle();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userRepository, times(1)).findAll();
    }
}
