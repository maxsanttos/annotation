package com.annotation.annotation.service;

import com.annotation.annotation.model.entity.User;
import com.annotation.annotation.model.entity.UserMapper;
import com.annotation.annotation.model.entity.UserRole;
import com.annotation.annotation.model.entity.dto.UserUpdateDTO;
import com.annotation.annotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
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

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new AuthenticationCredentialsNotFoundException("Usuário não autenticado");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            User user = (User) principal;
            Long userId = user.getId();
            return repository.findById(userId)
                    .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Usuário não encontrado: " + userId));
        }

        throw new AuthenticationCredentialsNotFoundException("Usuário não autenticado corretamente");
    }



    public User createUser(String name, String password, UserRole role) {
        System.out.println("Tentando criar usuário: " + name);

        if (repository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Nome de usuário já está em uso.");
        }

        User user = new User();
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        User saved = repository.save(user);
        System.out.println("Usuário salvo com ID: " + saved.getId());
        return saved;
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
