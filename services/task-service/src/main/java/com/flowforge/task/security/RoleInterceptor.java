package com.flowforge.task.security;

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
        String orgId  = request.getHeader("X-Org-Id");
        String role   = request.getHeader("X-Role");

        System.out.println("🔐 TASK Interceptor hit");
        System.out.println("UserId=" + userId);
        System.out.println("OrgId=" + orgId);
        System.out.println("Role=" + role);

        // Only validate identity presence
        if (userId == null || orgId == null || role == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        

        return true;
    }
}
