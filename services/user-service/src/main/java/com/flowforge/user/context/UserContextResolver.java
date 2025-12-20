package com.flowforge.user.context;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class UserContextResolver {

    public UserContext resolve(HttpServletRequest request) {

        String userId = request.getHeader("X-User-Id");
        String orgId  = request.getHeader("X-Org-Id");
        String role   = request.getHeader("X-Role");

        // ✅ Defensive fallback (prevents 500)
        if (userId == null) userId = "UNKNOWN";
        if (orgId == null)  orgId  = "UNKNOWN";
        if (role == null)   role   = "UNKNOWN";

        return new UserContext(userId, orgId, role);
    }
}
