package com.example.ws.infrastructure.shared.mappers;

import com.example.ws.application.dtos.UserApplication;
import com.example.ws.domain.entities.UserDomain;
import com.example.ws.infrastructure.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserApplication domainToApplication(UserDomain userDomain);
    UserDomain entityToDomain(UserEntity userEntity);
    List<UserApplication> domainListToApplicationList(List<UserDomain> all);

}
