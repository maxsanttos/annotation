package com.annotation.annotation.model.entity.dto;

import com.annotation.annotation.model.entity.UserRole;

public class UserResponseDTO {

    private Long id;
    private String name;
    private UserRole role;

    public UserResponseDTO(Long id, String name, UserRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }
}
