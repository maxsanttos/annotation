package com.annotation.annotation.model.entity.dto;

import com.annotation.annotation.model.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 6, max = 100)
    private String name;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, max = 100)
    private  String password;

    @NotNull(message = "Role é obrigatório")
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
