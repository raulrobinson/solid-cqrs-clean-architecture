package com.example.ws.application.dtos;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserApplicationTest {

    @Test
    void testNoArgsConstructor() {
        UserApplication user = new UserApplication();
        assertThat(user.getId()).isNull();
        assertThat(user.getName()).isNull();
        assertThat(user.getEmail()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        UserApplication user = new UserApplication(1L, "John Doe", "john.doe@example.com");

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("John Doe");
        assertThat(user.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    void testSettersAndGetters() {
        UserApplication user = new UserApplication();

        user.setId(2L);
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");

        assertThat(user.getId()).isEqualTo(2L);
        assertThat(user.getName()).isEqualTo("Jane Doe");
        assertThat(user.getEmail()).isEqualTo("jane.doe@example.com");
    }
}
