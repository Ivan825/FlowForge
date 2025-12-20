package com.flowforge.auth.service;

import com.flowforge.auth.dto.LoginRequest;
import com.flowforge.auth.dto.RegisterRequest;
import com.flowforge.auth.dto.RegisterUserRequest;
import com.flowforge.auth.entity.Organization;
import com.flowforge.auth.entity.UserCredentials;
import com.flowforge.auth.repository.OrganizationRepository;
import com.flowforge.auth.repository.UserRepository;
import com.flowforge.auth.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ✅ ADMIN REGISTRATION
    public void register(RegisterRequest request) {

        if (organizationRepository.existsByName(request.getOrganizationName())) {
            throw new RuntimeException("Organization already exists");
        }

        Organization org = organizationRepository.save(
                Organization.builder()
                        .name(request.getOrganizationName())
                        .build()
        );

        userRepository.save(
                UserCredentials.builder()
                        .email(request.getAdminEmail())
                        .passwordHash(passwordEncoder.encode(request.getPassword()))
                        .organizationId(org.getId().toString())
                        .roleId("ADMIN")
                        .build()
        );
    }

    // ✅ USER REGISTRATION (NEW — FIXED)
    public void registerUser(RegisterUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        userRepository.save(
                UserCredentials.builder()
                        .email(request.getEmail())
                        .passwordHash(passwordEncoder.encode(request.getPassword()))
                        .organizationId(request.getOrganizationId())
                        .roleId("USER")
                        .build()
        );
    }

    // ✅ LOGIN
    public String login(LoginRequest request) {

        UserCredentials user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(
                user.getId().toString(),
                user.getOrganizationId(),
                user.getRoleId()
        );
    }
}

