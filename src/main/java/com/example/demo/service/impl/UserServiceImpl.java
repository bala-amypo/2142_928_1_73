package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    @Transactional
    public User registerUser(User user, String roleName) {
        // Check if user already exists
        if (userRepository.existsByUsername(user.getUsername()) || 
            userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        
        // Find or create role
        Role role = roleRepository.findByName(roleName)
            .orElseGet(() -> {
                Role newRole = new Role();
                newRole.setName(roleName);
                return roleRepository.save(newRole);
            });
        
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Set roles
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        user.getRoles().add(role);
        
        return userRepository.save(user);
    }
    
    @Override
    public User findByUsername(String username) {
        // Return null instead of throwing exception (for AuthController to check)
        return userRepository.findByUsername(username).orElse(null);
    }
    
    @Override
    public User findByEmail(String email) {
        // Return null instead of throwing exception (for AuthController to check)
        return userRepository.findByEmail(email).orElse(null);
    }
    
    // Other business methods can be added here
}