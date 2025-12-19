package com.flowforge.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public Map<String, String> me(HttpServletRequest request) {

        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from User Service");
        response.put("userId", request.getHeader("X-User-Id"));
        response.put("orgId", request.getHeader("X-Org-Id"));
        response.put("role", request.getHeader("X-Role"));

        return response;
    }
}
