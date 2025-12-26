public String generateToken(Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    
    // You need to get the custom User entity, not cast directly
    // This is tricky because you only have username from UserDetails
    
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpiration);
    
    // Get authorities/roles from UserDetails
    String roles = userDetails.getAuthorities().stream()
        .map(auth -> auth.getAuthority().replace("ROLE_", ""))
        .collect(Collectors.joining(","));
    
    return Jwts.builder()
        .setSubject(userDetails.getUsername())  // Use username as subject
        .claim("username", userDetails.getUsername())
        .claim("roles", roles)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(getSigningKey(), SignatureAlgorithm.HS512)
        .compact();
}