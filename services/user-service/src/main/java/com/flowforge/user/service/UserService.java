package com.flowforge.user.service;

import com.flowforge.user.entity.User;
import com.flowforge.user.repository.UserRepository;
import com.flowforge.user.security.OrgContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getOrCreateUser(HttpServletRequest request) {

        String userId = request.getHeader("X-User-Id");
        String email  = request.getHeader("X-Email");   // optional
        String role   = request.getHeader("X-Role");    
        String orgId  = OrgContext.getOrgId();

        return repository
                .findByIdAndOrganizationId(userId, orgId)
                .orElseGet(() -> {
                    User u = new User();
                    u.setId(userId);
                    u.setEmail(email);
                    u.setOrganizationId(orgId);
                    u.setRole(role);                    
                    return repository.save(u);
                });
    }
}
