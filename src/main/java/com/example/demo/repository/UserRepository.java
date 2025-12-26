package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);  // Must have this!
    boolean existsByUsername(String username);  // For checking duplicates
    boolean existsByEmail(String email);        // For checking duplicates
}