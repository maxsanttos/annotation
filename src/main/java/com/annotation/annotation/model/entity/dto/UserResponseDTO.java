package com.annotation.annotation.model.entity.dto;

import com.annotation.annotation.model.entity.UserRole;

public class UserResponseDTO {

    private Long id;
    private String name;
    private UserRole role;

    public UserResponseDTO() {
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
