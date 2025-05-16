package com.annotation.annotation.model.entity.dto;

import com.annotation.annotation.model.entity.UserRole;
import jakarta.validation.constraints.Size;

public class UserUpdateDTO {

    @Size(min = 6, max = 50)
    private  String name;
    @Size(min = 6, max = 100)
    private String password;

    private UserRole role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
