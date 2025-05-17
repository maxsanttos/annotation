package com.annotation.annotation.model.entity.dto;

public class UserWithTokenDTO {

    private UserResponseDTO user;
    private String token;

    public UserWithTokenDTO(UserResponseDTO user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
