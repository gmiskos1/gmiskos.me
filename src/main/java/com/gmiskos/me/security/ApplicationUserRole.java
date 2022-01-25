package com.gmiskos.me.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.gmiskos.me.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(BLOG_READ, BLOG_WRITE, MEMBER_READ, MEMBER_WRITE)),
    MEMBER(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
