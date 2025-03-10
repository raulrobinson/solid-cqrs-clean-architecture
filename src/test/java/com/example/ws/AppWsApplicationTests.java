package com.example.ws;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AppWsApplicationTests {

    @Test
    void contextLoads() {
        assertThat(true).isTrue();
    }

}
