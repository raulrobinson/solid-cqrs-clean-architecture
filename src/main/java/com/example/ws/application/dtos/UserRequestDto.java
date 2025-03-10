package com.example.ws.application.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserRequestDto {
    @Schema(description = "User name", example = "John Doe")
    private String name;

    @Schema(description = "User email", example = "john.doe@email.com")
    private String email;

    public UserRequestDto(String newName, String mail) {
        this.name = newName;
        this.email = mail;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
