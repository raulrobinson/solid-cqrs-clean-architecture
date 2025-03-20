package com.example.ws.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class CsrfFilter extends OncePerRequestFilter {

    private final CsrfTokenRepository csrfTokenRepository;
    private static final List<String> CSRF_IGNORED_PATHS = List.of(
            "/api/v1/clients",
            "/h2-console"
    );

    private static final Pattern CSRF_IGNORED_PATTERN = Pattern.compile("^/api/v1/clients/\\d+$");

    public CsrfFilter() {
        this.csrfTokenRepository = new HttpSessionCsrfTokenRepository();
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpRequest,
                                    @NonNull HttpServletResponse httpResponse,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String requestURI = httpRequest.getRequestURI();

        if (CSRF_IGNORED_PATHS.stream().anyMatch(requestURI::startsWith) || CSRF_IGNORED_PATTERN.matcher(requestURI).matches()) {
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }

        CsrfToken csrfToken = csrfTokenRepository.loadToken(httpRequest);

        if (csrfToken == null) {
            csrfToken = csrfTokenRepository.generateToken(httpRequest);
            csrfTokenRepository.saveToken(csrfToken, httpRequest, httpResponse);
        }

        httpResponse.setHeader("X-CSRF-TOKEN", csrfToken.getToken());

        filterChain.doFilter(httpRequest, httpResponse);
    }

}
