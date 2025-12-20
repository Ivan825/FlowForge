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
    ) {

        String userId = request.getHeader("X-User-Id");
        String orgId  = OrgContext.getOrgId();
        String role   = RoleContext.getRole();

        System.out.println("🔐 Interceptor hit");
        System.out.println("UserId=" + userId);
        System.out.println("OrgId=" + orgId);
        System.out.println("Role=" + role);

        // 1️⃣ Missing identity → UNAUTHORIZED
        if (userId == null || orgId == null || role == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 2️⃣ ADMIN-only endpoint
        if (request.getRequestURI().equals("/users/me")) {
            if (!RoleContext.isAdmin()) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }

        return true;
    }
}
