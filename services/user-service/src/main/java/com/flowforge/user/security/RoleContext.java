package com.flowforge.user.security;

public final class RoleContext {

    private static final ThreadLocal<String> ROLE = new ThreadLocal<>();

    private RoleContext() {}

    public static void setRole(String role) {
        ROLE.set(role);
    }

    public static String getRole() {
        return ROLE.get();
    }

    public static boolean isAdmin() {
        return "ADMIN".equals(ROLE.get());
    }

    public static void clear() {
        ROLE.remove();
    }
}
