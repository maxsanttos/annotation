package com.annotation.annotation.model.entity;

import com.annotation.annotation.model.entity.dto.UserResponseDTO;
import com.annotation.annotation.model.entity.dto.UserUpdateDTO;

public class UserMapper {

    public static UserResponseDTO toDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        return dto;
    }

    public static void updateEntity(User user, UserUpdateDTO dto){
        if (dto.getName() != null && !dto.getName().isBlank()){
            user.setName(dto.getName());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(dto.getPassword());
        }

        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
    }
}
