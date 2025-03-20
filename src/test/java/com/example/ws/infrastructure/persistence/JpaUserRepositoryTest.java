package com.example.ws.infrastructure.persistence;

import com.example.ws.application.dto.UserRequestDto;
import com.example.ws.domain.entities.UserDomain;
import com.example.ws.infrastructure.shared.exceptions.*;
import com.example.ws.application.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaUserRepositoryTest {

    @Mock
    private SpringDataUserRepository repository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private JpaUserRepository jpaUserRepository;

    private UserDomain userDomain;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userDomain = new UserDomain(1L, "John Doe", "john@example.com");
        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("John Doe");
        userEntity.setEmail("john@example.com");
    }

    @Test
    void save_ShouldThrowException_WhenUserAlreadyExists() {
        when(repository.findUserEntityByEmail(userDomain.getEmail())).thenReturn(userEntity);

        assertThrows(CreateUserException.class, () -> jpaUserRepository.save(userDomain));
    }

    @Test
    void save_ShouldSaveUser_WhenUserDoesNotExist() {
        when(repository.findUserEntityByEmail(userDomain.getEmail())).thenReturn(null);
        when(repository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.entityToDomain(any(UserEntity.class))).thenReturn(userDomain);

        UserDomain result = jpaUserRepository.save(userDomain);
        assertNotNull(result);
        assertEquals(userDomain.getEmail(), result.getEmail());
    }

    @Test
    void delete_ShouldThrowException_WhenUserNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> jpaUserRepository.delete(1L));
    }

    @Test
    void delete_ShouldDeleteUser_WhenUserExists() {
        when(repository.existsById(1L)).thenReturn(true).thenReturn(false);

        assertTrue(jpaUserRepository.delete(1L));
    }

    @Test
    void findById_ShouldThrowException_WhenUserNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> jpaUserRepository.findById(1L));
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userMapper.entityToDomain(userEntity)).thenReturn(userDomain);

        UserDomain result = jpaUserRepository.findById(1L);
        assertNotNull(result);
        assertEquals(userDomain.getId(), result.getId());
    }

    @Test
    void findAll_ShouldThrowException_WhenNoUsersFound() {
        when(repository.findAll()).thenReturn(List.of());

        assertThrows(GlobalException.class, () -> jpaUserRepository.findAll());
    }

    @Test
    void findAll_ShouldReturnUsers_WhenUsersExist() {
        when(repository.findAll()).thenReturn(List.of(userEntity));
        when(userMapper.entityToDomain(userEntity)).thenReturn(userDomain);

        List<UserDomain> result = jpaUserRepository.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void update_ShouldThrowException_WhenUserNotFound() {
        UserRequestDto requestDto = new UserRequestDto("New Name", "new@example.com");
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> jpaUserRepository.update(1L, requestDto));
    }

    @Test
    void update_ShouldThrowException_WhenEmailAlreadyExists() {
        UserRequestDto requestDto = new UserRequestDto("New Name", "new@example.com");
        when(repository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(repository.findUserEntityByEmail(requestDto.getEmail())).thenReturn(new UserEntity());

        assertThrows(UpdateUserException.class, () -> jpaUserRepository.update(1L, requestDto));
    }

    @Test
    void update_ShouldUpdateUser_WhenValid() {
        UserRequestDto requestDto = new UserRequestDto("New Name", "new@example.com");
        when(repository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(repository.findUserEntityByEmail(requestDto.getEmail())).thenReturn(null);
        when(repository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.entityToDomain(userEntity)).thenReturn(userDomain);

        UserDomain result = jpaUserRepository.update(1L, requestDto);
        assertNotNull(result);
        assertEquals(userDomain.getId(), result.getId());
    }
}