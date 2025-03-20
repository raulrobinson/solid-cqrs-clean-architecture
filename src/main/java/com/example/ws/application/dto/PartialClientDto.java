package com.example.ws.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PartialClientDto", description = "Partial Client DTO")
public class PartialClientDto {

    @Schema(description = "Status of the client", example = "false")
    private boolean isActive;
}
