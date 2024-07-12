package br.com.emerlopes.shoppingcart.application.response;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    ADMIN("ROLE_ADMIN"),

    USER("ROLE_USER"),

    GUEST("ROLE_GUEST");

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public static UserRoleEnum fromRole(String role) {
        for (UserRoleEnum userRole : values()) {
            if (userRole.role.equals(role)) {
                return userRole;
            }
        }
        return null;
    }
}
