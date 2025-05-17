package com.annotation.annotation.configuration;

import com.annotation.annotation.model.entity.User;
import com.annotation.annotation.model.entity.UserRole;
import com.annotation.annotation.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.NoSuchElementException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Autowired
    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("SecurityFilter chamado");

        String token = recoverToken(request);

        if (token != null && !token.isEmpty()) {
            String userIdStr = tokenService.validateToken(token);

            if (userIdStr != null && !userIdStr.isEmpty()) {
                try {
                    Long userId = Long.valueOf(userIdStr);
                    User user = userRepository.findById(userId).orElse(null);

                    System.out.println("Usuário recuperado: " + (user != null ? user.getName() : "null"));

                    if (user != null) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("Usuário autenticado: " + user.getName());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID do usuário inválido no token");
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.substring(7); // remove "Bearer "
    }
}
