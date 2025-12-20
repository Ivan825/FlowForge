package com.flowforge.user.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        String userId = request.getHeader("X-User-Id");
        String orgId  = request.getHeader("X-Org-Id");
        String role   = request.getHeader("X-Role");

        System.out.println("🔐 Interceptor hit");
        System.out.println("UserId=" + userId);
        System.out.println("OrgId=" + orgId);
        System.out.println("Role=" + role);

        // 1️⃣ Missing identity → UNAUTHORIZED
        if (userId == null || orgId == null || role == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String path = request.getRequestURI();
        String method = request.getMethod();

        // 2️⃣ ADMIN-only endpoints
        boolean adminOnly =
                path.equals("/users/me") ||
                path.equals("/users");

        if (adminOnly && !"ADMIN".equals(role)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        return true; // allow request
    }
}
