package com.flowforge.user.controller;

import com.flowforge.user.entity.User;
import com.flowforge.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // Existing endpoint
    @GetMapping("/me")
    public User me(HttpServletRequest request) {
        return service.getOrCreateUser(request);
    }

    // 🆕 ADMIN: list users
    @GetMapping
    public List<User> listUsers() {
        return service.listUsers();
    }

    // 🆕 ADMIN: create user
    @PostMapping
    public User createUser(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String role  = body.getOrDefault("role", "USER");

        return service.createUser(email, role);
    }
}
