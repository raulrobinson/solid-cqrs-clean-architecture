package com.example.ws.application.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateUserUseCaseTest {

//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UpdateUserUseCase updateUserUseCase;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void execute_ShouldUpdateUserSuccessfully() {
//        // Arrange
//        Long userId = 1L;
//        UserRequestDto request = new UserRequestDto("Updated Name", "updated.email@example.com");
//        UserDomain updatedUser = new UserDomain.UserBuilder()
//                .id(userId)
//                .name(request.getName())
//                .email(request.getEmail())
//                .build();
//
//        when(userRepository.update(eq(userId), any(UserRequestDto.class))).thenReturn(updatedUser);
//
//        // Act
//        UserDomain result = updateUserUseCase.execute(userId, request);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(request.getName(), result.getName());
//        assertEquals(request.getEmail(), result.getEmail());
//
//        verify(userRepository, times(1)).update(eq(userId), any(UserRequestDto.class));
//    }
}
