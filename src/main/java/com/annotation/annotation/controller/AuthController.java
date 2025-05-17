package com.annotation.annotation.controller;

import com.annotation.annotation.configuration.TokenService;
import com.annotation.annotation.model.entity.User;
import com.annotation.annotation.model.entity.dto.JwtAuthenticationResponse;
import com.annotation.annotation.model.entity.dto.LoginRequestDTO;
import com.annotation.annotation.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        // Autentica o usuário
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        // Define o contexto de segurança
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Busca o usuário para gerar o token com mais dados (se necessário)
        User user = userRepository.findByName(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Gera o token JWT
        String token = tokenService.generateToken(user);

        // Retorna o token encapsulado em DTO
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

}
