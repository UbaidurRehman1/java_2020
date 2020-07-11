package com.ubaid.enums;

public enum Employee {

    ADMIN("admin"),
    USER("user");

    private Employee(String role) {
        this.role = role;
    }

    private String role;

    public String getRole() { return role; }
}
