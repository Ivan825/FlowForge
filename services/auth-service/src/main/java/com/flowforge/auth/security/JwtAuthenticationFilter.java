package com.flowforge.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getRequestURI();

        // ✅ VERY IMPORTANT: skip auth endpoints
        if (path.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // For now, auth-service does NOT need JWT validation
        // (Gateway already handles it)

        filterChain.doFilter(request, response);
    }
}