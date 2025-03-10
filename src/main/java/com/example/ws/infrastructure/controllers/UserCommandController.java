package com.example.ws.infrastructure.controllers;

import com.example.ws.application.dtos.UserRequestDto;
import com.example.ws.application.usecases.CreateUserUseCase;
import com.example.ws.application.usecases.DeleteUserUseCase;
import com.example.ws.application.usecases.UpdateUserUseCase;
import com.example.ws.infrastructure.shared.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${controller.properties.base-path}/users")
@Tag(name = "Users Command", description = "User Command Operations")
public class UserCommandController {

    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    public UserCommandController(CreateUserUseCase createUserUseCase, DeleteUserUseCase deleteUserUseCase, UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @PostMapping("/create-user")
    @Operation(summary = "Create user", description = "Create user")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(UserMapper.INSTANCE.domainToApplication(createUserUseCase.execute(userRequestDto)));
    }

    @DeleteMapping("/delete-user/{id}")
    @Operation(summary = "Delete user", description = "Delete user")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(deleteUserUseCase.execute(id));
    }

    @PatchMapping("/update-user/{id}")
    @Operation(summary = "Update user", description = "Update user")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(UserMapper.INSTANCE.domainToApplication(updateUserUseCase.execute(id, userRequestDto)));
    }
}
