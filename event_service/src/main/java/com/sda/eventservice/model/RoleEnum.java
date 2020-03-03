package com.sda.eventservice.model;

import java.util.Arrays;

public enum RoleEnum {

    USER("ROLE_USER"),
    ORGANIZER("ROLE_ORGANIZER"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static RoleEnum getRole(String role) {
        return Arrays.stream(RoleEnum.values())
                .filter(r -> r.getRole().equals(role))
                .findFirst()
                .orElse(RoleEnum.USER);
    }


}
