package com.example.ws.application.usecases;

import com.example.ws.application.dto.UserRequestDto;
import com.example.ws.domain.entities.UserDomain;
import com.example.ws.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {
    private final UserRepository repository;

    public UpdateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public UserDomain execute(Long id, UserRequestDto userRequestDto) {
        return repository.update(id, userRequestDto);
    }
}
