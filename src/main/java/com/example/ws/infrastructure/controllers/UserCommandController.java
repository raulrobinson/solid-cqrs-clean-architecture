package com.example.ws.infrastructure.controllers;

import com.example.ws.application.dto.UserRequestDto;
import com.example.ws.application.usecases.CreateUserUseCase;
import com.example.ws.application.usecases.DeleteUserUseCase;
import com.example.ws.application.usecases.UpdateUserUseCase;
import com.example.ws.infrastructure.shared.exceptions.CreateUserException;
import com.example.ws.infrastructure.shared.exceptions.DeleteUserException;
import com.example.ws.infrastructure.shared.exceptions.UpdateUserException;
import com.example.ws.application.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
    public ResponseEntity<?> createUser(final @RequestBody UserRequestDto userRequestDto) {
        if (userRequestDto == null)
            throw new CreateUserException("Request not found");
        if (Objects.equals(userRequestDto.getName(), "") || Objects.equals(userRequestDto.getEmail(), ""))
            throw new CreateUserException("Incomplete request");
        return ResponseEntity.ok(UserMapper.INSTANCE.domainToApplication(createUserUseCase.execute(userRequestDto)));
    }

    @DeleteMapping("/delete-user/{id}")
    @Operation(summary = "Delete user", description = "Delete user")
    public ResponseEntity<?> deleteUser(final @PathVariable Long id) {
        if (id == null) throw new DeleteUserException("ID not found in request");
        return ResponseEntity.ok(deleteUserUseCase.execute(id));
    }

    @PatchMapping("/update-user/{id}")
    @Operation(summary = "Update user", description = "Update user")
    public ResponseEntity<?> updateUser(final @PathVariable Long id,
                                        final @RequestBody UserRequestDto userRequestDto) {
        if (id == null) throw new UpdateUserException("ID not found in request");
        if (userRequestDto == null) throw new UpdateUserException("Request not found");
        if (Objects.equals(userRequestDto.getName(), "") || Objects.equals(userRequestDto.getEmail(), ""))
            throw new UpdateUserException("Incomplete request");
        return ResponseEntity.ok(UserMapper.INSTANCE.domainToApplication(updateUserUseCase.execute(id, userRequestDto)));
    }
}
