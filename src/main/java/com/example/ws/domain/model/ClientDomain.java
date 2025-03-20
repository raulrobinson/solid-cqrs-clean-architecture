package com.example.ws.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDomain {

    private UUID id;
    private String dni;
    private String name;
    private String password;
    private String gender;
    private int age;
    private String address;
    private String phone;
    private boolean isActive;
    private String clientCode;
    private String clientType;
    private String dniType;
}
