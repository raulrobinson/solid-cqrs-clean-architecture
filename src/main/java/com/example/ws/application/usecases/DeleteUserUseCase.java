package com.example.ws.application.usecases;

import com.example.ws.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserUseCase {
    private final UserRepository repository;

    public DeleteUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public Boolean execute(Long id) {
        return repository.delete(id);
    }
}
