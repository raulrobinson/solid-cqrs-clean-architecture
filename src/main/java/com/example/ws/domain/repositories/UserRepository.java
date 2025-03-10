package com.example.ws.domain.repositories;

import com.example.ws.application.dtos.UserRequestDto;
import com.example.ws.domain.entities.UserDomain;

import java.util.List;

public interface UserRepository {
    UserDomain save(UserDomain userDomain);
    Boolean delete(Long id);
    UserDomain findById(Long id);
    List<UserDomain> findAll();
    UserDomain update(Long id, UserRequestDto userRequestDto);
}
