package com.flowforge.task.security;

import com.flowforge.task.entity.Task;
import com.flowforge.task.exception.ForbiddenException;

import org.springframework.stereotype.Component;

@Component
public class TaskAccessValidator {

    // ADMIN only
    public void assertCanCreate(String role) {
        if (!"ADMIN".equals(role)) {
            throw new ForbiddenException("Only admins can create tasks");

        }
    }

    // ADMIN only
    public void assertCanAssign(String role) {
        if (!"ADMIN".equals(role)) {
            throw new ForbiddenException("Only admins can create tasks");

        }
    }

    // BOTH ADMIN + USER
    public void assertCanView(Task task, String orgId) {
        if (!task.getOrganizationId().equals(orgId)) {
            throw new RuntimeException("Cross-org access denied");
        }
    }

    // USER can update ONLY own task, ADMIN can update all
    public void assertCanUpdate(Task task, String role, String userId) {
        if ("ADMIN".equals(role)) return;

        if ("USER".equals(role) && userId.equals(task.getAssignedTo())) {
            return;
        }

        throw new RuntimeException("Not allowed to update this task");
    }
}
