package com.flowforge.user.security;

public final class OrgContext {

    private static final ThreadLocal<String> ORG = new ThreadLocal<>();

    private OrgContext() {}

    public static void setOrgId(String orgId) {
        ORG.set(orgId);
    }

    public static String getOrgId() {
        return ORG.get();
    }

    public static void clear() {
        ORG.remove();
    }
}
