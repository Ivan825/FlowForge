package com.flowforge.user.context;

public record UserContext(
        String userId,
        String orgId,
        String role
) {}
