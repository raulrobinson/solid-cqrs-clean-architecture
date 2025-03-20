package com.example.ws.infrastructure.shared.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GlobalError {

    private String message;
    private String path;
    private LocalDateTime timestamp;
    private int status;
    private String errorReason;
}