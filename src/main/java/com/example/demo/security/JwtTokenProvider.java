package com.example.demo.security;

import com.example.demo.model.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final String jwtSecret = SecurityConstants.SECRET_KEY;
    private final long jwtExpirationMs = SecurityConstants.EXPIRATION_TIME; // 24 hours

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim(
                    "roles",
                    user.getRoles()
                        .stream()
                        .map(role -> role.getName())  // Fixed: 'role' not 'Role'
                        .collect(Collectors.toList())
                )
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        try {
            return parseClaims(token).getSubject();
        } catch (Exception e) {
            return null;
        }
    }
   
    public Long getUserIdFromToken(String token) {
        try {
            Object value = parseClaims(token).get("userId");
            return value == null ? null : Long.valueOf(value.toString());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}