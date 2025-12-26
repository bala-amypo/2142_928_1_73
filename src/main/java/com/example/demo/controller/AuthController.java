@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(),
                loginRequest.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        
        // Get the authenticated user's details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Load your custom User entity from database
        User user = userService.findByUsername(userDetails.getUsername());
        
        List<String> roleNames = user.getRoles().stream()
            .map(role -> role.getName())
            .collect(Collectors.toList());
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("type", "Bearer");
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("roles", roleNames);
        
        return ResponseEntity.ok(response);
        
    } catch (Exception e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Invalid username/email or password");
        errorResponse.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}