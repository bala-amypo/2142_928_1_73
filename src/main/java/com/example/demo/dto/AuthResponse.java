package com.example.demo.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AuthResponse {
    private String token;
    private String username;
    private Set<String> roles;
}