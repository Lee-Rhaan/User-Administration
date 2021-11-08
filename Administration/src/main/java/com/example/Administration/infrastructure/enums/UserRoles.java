package com.example.Administration.infrastructure.enums;

public enum UserRoles {
    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String userRole;

    UserRoles(String userRole)
    {
        this.userRole = userRole;
    }

    public String getUserRole()
    {
        return userRole;
    }
}
