package com.example.ws.infrastructure.persistence;

import com.example.ws.application.dto.UserRequestDto;
import com.example.ws.domain.entities.UserDomain;
import com.example.ws.domain.repositories.UserRepository;
import com.example.ws.infrastructure.shared.exceptions.*;
import com.example.ws.application.mapper.UserMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JpaUserRepository.class);

    private final SpringDataUserRepository repository;
    private final UserMapper userMapper;

    public JpaUserRepository(SpringDataUserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDomain save(UserDomain userDomain) {
        UserEntity userEntity = repository.findUserEntityByEmail(userDomain.getEmail());
        if (userEntity != null) {
            throw new CreateUserException("User already exists with e-mail: " + userDomain.getEmail());
        }
        UserEntity entity = new UserEntity();
        entity.setName(userDomain.getName());
        entity.setEmail(userDomain.getEmail());
        UserEntity savedEntity = repository.save(entity);
        if (savedEntity.getId() != 0) {
            log.info("User with ID {} saved", entity.getId());
            return userMapper.entityToDomain(entity);
        }
        throw new GlobalException("User not saved");
    }

    @Override
    public Boolean delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        repository.deleteById(id);
        if (repository.existsById(id)) {
            throw new DeleteUserException("User with ID " + id + " not deleted");
        }
        log.info("User with ID {} deleted", id);
        return true;
    }

    @Override
    public UserDomain findById(Long id) {
        UserEntity entity = repository.findById(id).orElse(null);
        if (entity == null) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        log.info("User with ID {} found", id);
        return userMapper.entityToDomain(entity);
    }

    @Override
    public List<UserDomain> findAll() {
        List<UserDomain> users = repository.findAll().stream()
                .map(userMapper::entityToDomain)
                .toList();
        if (users.isEmpty()) {
            throw new GlobalException("No users found");
        }
        log.info("Found {} users", users.size());
        return users;
    }

    @Override
    public UserDomain update(Long id, UserRequestDto userRequestDto) {
        Optional<UserEntity> entity = repository.findById(id);
        UserEntity userEntity = repository.findUserEntityByEmail(userRequestDto.getEmail());
        if (entity.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        } else if (userEntity != null) {
            throw new UpdateUserException("User already exists with e-mail: " + userRequestDto.getEmail());
        }
        entity.get().setName(userRequestDto.getName());
        entity.get().setEmail(userRequestDto.getEmail());
        UserEntity updatedEntity = repository.save(entity.get());
        if (Objects.equals(updatedEntity.getId(), id)) {
            log.info("User with ID {} updated", id);
            return userMapper.entityToDomain(updatedEntity);
        }
        throw new GlobalException("User with ID " + id + " not updated");
    }

}
