package com.flowforge.user.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class OrgInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        String orgId = request.getHeader("X-Org-Id");

        if (orgId == null || orgId.isBlank()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        // 🔐 Store orgId for downstream use
        request.setAttribute("ORG_ID", orgId);

        return true;
    }
}
