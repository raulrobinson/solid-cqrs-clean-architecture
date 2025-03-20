package com.example.ws.application.usecases;

import com.example.ws.application.dto.UserRequestDto;
import com.example.ws.domain.entities.UserDomain;
import com.example.ws.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
    private final UserRepository repository;

    public CreateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public UserDomain execute(UserRequestDto command) {
        UserDomain userDomain = new UserDomain.UserBuilder()
                .name(command.getName())
                .email(command.getEmail())
                .build();
        return repository.save(userDomain);
    }
}
