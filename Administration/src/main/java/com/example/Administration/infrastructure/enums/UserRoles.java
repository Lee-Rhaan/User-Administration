package com.example.Administration.infrastructure.enums;

/**
 * This enum class allows me to set the Roles of the users in this Administration application
 * with predefined enum constants
 */

public enum UserRoles {
    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private final String userRole;

    /**
     * Initializing userRole variable with argument provided in parameters
     *
     * @param userRole String
     */
    UserRoles(String userRole)
    {
        this.userRole = userRole;
    }

    /**
     * @return role of specified user
     */
    public String getUserRole()
    {
        return userRole;
    }
}
