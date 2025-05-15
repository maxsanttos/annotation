package com.annotation.annotation.repository;

import com.annotation.annotation.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByName(String login);

    User findUserByName(String name);
}
