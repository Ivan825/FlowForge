package com.flowforge.user.controller;

import com.flowforge.user.entity.User;
import com.flowforge.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public Map<String, String> me(HttpServletRequest request) {

        // ✅ THIS WAS THE MISSING STEP
        User user = userService.getOrCreateUser(request);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from User Service");
        response.put("userId", user.getId());
        response.put("orgId", user.getOrganizationId());
        response.put("role", user.getRole());

        return response;
    }
}
