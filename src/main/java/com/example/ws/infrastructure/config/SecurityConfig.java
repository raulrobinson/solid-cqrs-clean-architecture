package com.example.ws.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${controller.properties.base-path}")
    private String basePath;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .addFilterBefore(new CsrfFilter(), ChannelProcessingFilter.class)

                // [AUTHORIZATION]
                // The authorizeHttpRequests method allows configuring the
                // authorization rules for the application.
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests

                        // [SWAGGER]
                        // Swagger is a set of open-source tools built around the OpenAPI Specification
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/api-docs/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()

                        // [CLIENTS]
                        // Clients operations are allowed for all users
                        .requestMatchers(HttpMethod.GET, basePath + "/clients/dni/*").permitAll()
                        .requestMatchers(HttpMethod.GET, basePath + "/clients/client-code/*").permitAll()
                        .requestMatchers(HttpMethod.GET, basePath + "/clients").permitAll()
                        .requestMatchers(HttpMethod.POST, basePath + "/clients").permitAll()
                        .requestMatchers(HttpMethod.PUT, basePath + "/clients/*").permitAll()
                        .requestMatchers(HttpMethod.PATCH, basePath + "/clients/status/*").permitAll()
                        .requestMatchers(HttpMethod.DELETE, basePath + "/clients/*").permitAll()
                        .anyRequest().authenticated()
                )

                // [CSRF]
                // Cross-Site Request Forgery (CSRF) is an attack that forces an
                // end user to execute unwanted actions on
                // a web application in which they're currently authenticated.
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                "/h2-console/**",
                                basePath + "/clients",
                                basePath + "/clients/*",
                                basePath + "/clients/status/*"
                        )
                )

                // [FRAME OPTIONS]
                // The X-Frame-Options HTTP response header can be used to indicate
                // whether a browser should be allowed to render a page in a
                // <frame>, <iframe>, <embed> or <object>.
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                );

        return http.build();
    }

}
