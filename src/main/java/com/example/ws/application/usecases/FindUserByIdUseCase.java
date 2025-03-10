package com.example.ws.application.usecases;

import com.example.ws.domain.entities.UserDomain;
import com.example.ws.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FindUserByIdUseCase {
    private final UserRepository repository;

    public FindUserByIdUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public UserDomain handle(Long id) {
        return repository.findById(id);
    }
}
