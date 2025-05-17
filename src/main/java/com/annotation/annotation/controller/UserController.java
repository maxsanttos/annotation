package com.annotation.annotation.controller;

import com.annotation.annotation.configuration.TokenService;
import com.annotation.annotation.model.entity.User;
import com.annotation.annotation.model.entity.UserMapper;
import com.annotation.annotation.model.entity.UserRole;
import com.annotation.annotation.model.entity.dto.UserRequestDTO;
import com.annotation.annotation.model.entity.dto.UserResponseDTO;
import com.annotation.annotation.model.entity.dto.UserUpdateDTO;
import com.annotation.annotation.model.entity.dto.UserWithTokenDTO;
import com.annotation.annotation.repository.UserRepository;
import com.annotation.annotation.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService service;
    private final TokenService tokenService;

    public UserController(UserService service, TokenService tokenService, UserRepository userRepository) {
        this.service = service;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerWithToken(@Valid @RequestBody UserRequestDTO dto) {
        try {
            User user = service.createUser(dto.getName(), dto.getPassword(), dto.getRole());
            String token = tokenService.generateToken(user);
            UserResponseDTO userDTO = UserMapper.toDTO(user);
            return new ResponseEntity<>(new UserWithTokenDTO(userDTO, token), HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {
        User updatedUser = service.updateUser(id, dto.getName(), dto.getPassword(), dto.getRole());
        return ResponseEntity.ok(UserMapper.toDTO(updatedUser));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        List<UserResponseDTO> response = service.getAllUsers().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = service.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }



}
