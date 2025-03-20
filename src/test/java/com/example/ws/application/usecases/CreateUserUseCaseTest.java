package com.example.ws.application.usecases;

import com.example.ws.application.dto.UserRequestDto;
import com.example.ws.domain.entities.UserDomain;
import com.example.ws.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ShouldCreateUserSuccessfully() {
        // Arrange
        UserRequestDto request = new UserRequestDto("John Doe", "john.doe@example.com");
        UserDomain userDomain = new UserDomain.UserBuilder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        when(userRepository.save(any(UserDomain.class))).thenReturn(userDomain);

        // Act
        UserDomain result = createUserUseCase.execute(request);

        // Assert
        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getEmail(), result.getEmail());

        verify(userRepository, times(1)).save(any(UserDomain.class));
    }
}
