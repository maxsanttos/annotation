package com.annotation.annotation.service;

import com.annotation.annotation.model.entity.User;
import com.annotation.annotation.model.entity.UserMapper;
import com.annotation.annotation.model.entity.UserRole;
import com.annotation.annotation.model.entity.dto.UserUpdateDTO;
import com.annotation.annotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getLoggedInUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails){
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
         return repository.findByName(username)
                 .orElseThrow(() ->
                         new AuthenticationCredentialsNotFoundException("Usuário não autenticado ou credenciais inválidas."));
    }

    public User createUser(String name, String password, UserRole role) {
        if (repository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Nome de usuário já está em uso.");
        }

        User user = new User();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return repository.save(user);
    }

    public User updateUser(Long id, String name, String password, UserRole role) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com id: " + id));

        checkUsernameUniqueness(name, id);

        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setName(name);
        dto.setPassword(null);
        dto.setRole(role);

        UserMapper.updateEntity(user, dto);

        if (password != null && !password.isBlank()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com id: " + id));
    }

    public void deleteUserById(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Usuário não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }

    private void checkUsernameUniqueness(String name, Long currentUserId) {
        if (name != null && !name.isBlank()) {
            repository.findByName(name).ifPresent(existingUser -> {
                if (currentUserId == null || !existingUser.getId().equals(currentUserId)) {
                    throw new IllegalArgumentException("Nome de usuário já está em uso.");
                }
            });
        }
    }
}
