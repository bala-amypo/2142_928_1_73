package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {
    
    // Use hardcoded values instead of @Value injection
    public static final String SECRET = "MySecretKeyForJWTGeneration12345MySecretKeyForJWTGeneration12345";
    public static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds
    
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    
    public String getSecret() {
        return SECRET;
    }
    
    public long getExpirationTime() {
        return EXPIRATION_TIME;
    }
}