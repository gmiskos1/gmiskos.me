package com.gmiskos.me.security;

public enum ApplicationUserPermission {
    BLOG_READ("blog:read"),
    BLOG_WRITE("blog:write"),
    MEMBER_READ("member:read"),
    MEMBER_WRITE("member:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
