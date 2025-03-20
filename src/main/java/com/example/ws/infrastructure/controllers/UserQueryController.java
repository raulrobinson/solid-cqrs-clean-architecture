package com.example.ws.infrastructure.controllers;

import com.example.ws.application.usecases.FindUserByIdUseCase;
import com.example.ws.application.usecases.GetAllUsersUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ws.application.mapper.UserMapper;

@RestController
@RequestMapping("${controller.properties.base-path}/users")
@Tag(name = "Users Queries", description = "User Queries Operations")
public class UserQueryController {

    private final GetAllUsersUseCase getAllUsersUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;

    public UserQueryController(GetAllUsersUseCase getAllUsersUseCase, FindUserByIdUseCase findUserByIdUseCase) {
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
    }

    @GetMapping
    @Operation(summary = "Find all users", description = "Find all users")
    public ResponseEntity<?> findAllUsers() {
        return ResponseEntity.ok(UserMapper.INSTANCE.domainListToApplicationList(getAllUsersUseCase.handle()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find user by ID", description = "Find user by ID")
    public ResponseEntity<?> getUserById(final @PathVariable Long id) {
        if (id == null) return ResponseEntity.badRequest().body("ID not found in request");
        return ResponseEntity.ok(UserMapper.INSTANCE.domainToApplication(findUserByIdUseCase.handle(id)));
    }
}
