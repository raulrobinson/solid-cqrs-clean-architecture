package com.example.ws.infrastructure.shared.mappers;

import com.example.ws.application.dto.UserApplication;
import com.example.ws.application.mapper.UserMapper;
import com.example.ws.domain.entities.UserDomain;
import com.example.ws.infrastructure.persistence.UserEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static java.util.Collections.singletonList;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void testDomainToApplication() {
        UserDomain userDomain = new UserDomain(1L, "John Doe", "john.doe@example.com");

        UserApplication userApplication = mapper.domainToApplication(userDomain);

        assertThat(userApplication).isNotNull();
        assertThat(userApplication.getId()).isEqualTo(userDomain.getId());
        assertThat(userApplication.getName()).isEqualTo(userDomain.getName());
        assertThat(userApplication.getEmail()).isEqualTo(userDomain.getEmail());
    }

    @Test
    void testEntityToDomain() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(2L);
        userEntity.setName("Jane Doe");
        userEntity.setEmail("jane.doe@example.com");

        UserDomain userDomain = mapper.entityToDomain(userEntity);

        assertThat(userDomain).isNotNull();
        assertThat(userDomain.getId()).isEqualTo(userEntity.getId());
        assertThat(userDomain.getName()).isEqualTo(userEntity.getName());
        assertThat(userDomain.getEmail()).isEqualTo(userEntity.getEmail());
    }

    @Test
    void testDomainListToApplicationList() {
        UserDomain userDomain = new UserDomain(3L, "Alice", "alice@example.com");
        List<UserDomain> domainList = singletonList(userDomain);

        List<UserApplication> applicationList = mapper.domainListToApplicationList(domainList);

        assertThat(applicationList).isNotNull();
        assertThat(applicationList).hasSize(1);
        assertThat(applicationList.get(0).getId()).isEqualTo(userDomain.getId());
        assertThat(applicationList.get(0).getName()).isEqualTo(userDomain.getName());
        assertThat(applicationList.get(0).getEmail()).isEqualTo(userDomain.getEmail());
    }
}
