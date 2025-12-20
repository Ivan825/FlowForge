package com.flowforge.user.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class InternalAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            // ✅ TRUST ONLY GATEWAY HEADERS
            OrgContext.setOrgId(request.getHeader("X-Org-Id"));
            RoleContext.setRole(request.getHeader("X-Role"));

            filterChain.doFilter(request, response);
        } finally {
            // ✅ CRITICAL: prevent thread leakage
            OrgContext.clear();
            RoleContext.clear();
        }
    }
}
