package com.flowforge.auth.service;

import com.flowforge.auth.dto.RegisterRequest;
import com.flowforge.auth.entity.Organization;
import com.flowforge.auth.entity.UserCredentials;
import com.flowforge.auth.repository.OrganizationRepository;
import com.flowforge.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {

        if (organizationRepository.existsByName(request.getOrganizationName())) {
            throw new RuntimeException("Organization already exists");
        }

        Organization organization = organizationRepository.save(
                Organization.builder()
                        .name(request.getOrganizationName())
                        .build()
        );

        userRepository.save(
                UserCredentials.builder()
                        .email(request.getAdminEmail())
                        .passwordHash(passwordEncoder.encode(request.getPassword()))
                        .organizationId(organization.getId())
                        .roleId("ADMIN")
                        .build()
        );
    }
}
