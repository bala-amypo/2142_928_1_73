package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication and Registration APIs")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        
        User registeredUser = userService.registerUser(user, registerRequest.getRole());
        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/login")
    @Operation(summary = "Authenticate user and get JWT token")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequest.getUsernameOrEmail(),
                authRequest.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        User user = userService.findByUsername(authRequest.getUsernameOrEmail());
        String jwt = tokenProvider.generateToken(user);
        
        AuthResponse response = new AuthResponse();
        response.setToken(jwt);
        response.setUsername(user.getUsername());
        response.setRoles(user.getRoles().stream()
            .map(role -> role.getName())
            .collect(java.util.stream.Collectors.toSet()));
        
        return ResponseEntity.ok(response);
    }
}