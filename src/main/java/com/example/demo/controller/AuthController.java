package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/{roleName}")
    public User register(@RequestBody User user,
                         @PathVariable String roleName) {
        return userService.registerUser(user, roleName);
    }

    @PostMapping("/login")
    public String login() {
        // placeholder (JWT will be added later as per SRS phase)
        return "Login successful";
    }
}
