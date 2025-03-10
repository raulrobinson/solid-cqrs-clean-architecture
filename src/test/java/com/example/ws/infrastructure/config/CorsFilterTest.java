package com.example.ws.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CorsFilterTest {

    private CorsFilter corsFilter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        corsFilter = new CorsFilter();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = Mockito.mock(FilterChain.class);
    }

    @Test
    void shouldSetCorsHeaders() throws ServletException, IOException {
        request.setMethod("GET");

        corsFilter.doFilter(request, response, filterChain);

        assertEquals("*", response.getHeader("Access-Control-Allow-Origin"));
        assertEquals("GET, POST, PUT, DELETE, OPTIONS", response.getHeader("Access-Control-Allow-Methods"));
        assertEquals("Authorization, Content-Type", response.getHeader("Access-Control-Allow-Headers"));
        assertEquals("3600", response.getHeader("Access-Control-Max-Age"));

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void shouldHandleOptionsRequest() throws ServletException, IOException {
        request.setMethod("OPTIONS");

        corsFilter.doFilter(request, response, filterChain);

        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        verify(filterChain, never()).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));
    }
}
