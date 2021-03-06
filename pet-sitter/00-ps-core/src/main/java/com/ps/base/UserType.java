package com.ps.base;

/**
 * Type of users using the site
 */
public enum UserType {
    OWNER(1),
    SITTER(2),
    BOTH(3),
    ADMIN(4);

    private int code;

    UserType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
