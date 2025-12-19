package com.flowforge.auth.controller;

import com.flowforge.auth.dto.LoginRequest;
import com.flowforge.auth.dto.LoginResponse;
import com.flowforge.auth.dto.RegisterRequest;
import com.flowforge.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "Registered";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return new LoginResponse(authService.login(request));
    }
}
