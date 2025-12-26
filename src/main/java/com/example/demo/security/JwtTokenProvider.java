package com.example.demo.security;

import com.example.demo.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    
    private final SecurityConstants securityConstants;
    private final SecretKey key;
    
    @Autowired
    public JwtTokenProvider(SecurityConstants securityConstants) {
        this.securityConstants = securityConstants;
        // Use the secret from configuration
        this.key = Keys.hmacShaKeyFor(securityConstants.getSecret().getBytes());
    }
    
    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + securityConstants.getExpirationTime());
        
        String roles = user.getRoles().stream()
            .map(role -> role.getName())
            .collect(Collectors.joining(","));
        
        return Jwts.builder()
            .setSubject(Long.toString(user.getId()))
            .claim("username", user.getUsername())
            .claim("email", user.getEmail())
            .claim("roles", roles)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(key)
            .compact();
    }
    
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
        
        return Long.parseLong(claims.getSubject());
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}