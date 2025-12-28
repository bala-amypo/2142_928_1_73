package com.example.demo.security;

public class SecurityConstants {
    public static final String JWT_SECRET = "mySecretKey";
    public static final long JWT_EXPIRATION = 86400000L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}