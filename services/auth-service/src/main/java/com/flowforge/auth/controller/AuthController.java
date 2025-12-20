package com.flowforge.auth.controller;

import com.flowforge.auth.dto.LoginRequest;
import com.flowforge.auth.dto.LoginResponse;
import com.flowforge.auth.dto.RegisterRequest;
import com.flowforge.auth.dto.RegisterUserRequest;
import com.flowforge.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ✅ ADMIN REGISTER
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "Registered ADMIN";
    }

    // ✅ USER REGISTER (THIS WAS MISSING)
    @PostMapping("/register-user")
    public String registerUser(@RequestBody RegisterUserRequest request) {
        System.out.println(">>> REGISTER USER ENDPOINT HIT <<<");
        authService.registerUser(request);
        return "Registered USER";
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return new LoginResponse(authService.login(request));
    }
}
