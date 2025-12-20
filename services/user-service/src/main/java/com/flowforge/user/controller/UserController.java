package com.flowforge.user.controller;

import com.flowforge.user.entity.User;
import com.flowforge.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // ✅ Current logged-in user (ADMIN or USER)
    @GetMapping("/me")
    public User me(HttpServletRequest request) {
        return service.getOrCreateUser(request);
    }

    // ✅ ADMIN: list users in org
    @GetMapping
    public List<User> listUsers() {
        return service.listUsers();
    }

    // ✅ ADMIN: create user in org
    @PostMapping
    public User createUser(@RequestBody CreateUserRequest body) {
        return service.createUser(
                body.getEmail(),
                body.getRole() == null ? "USER" : body.getRole()
        );
    }
}
