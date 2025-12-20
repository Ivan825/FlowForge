package com.flowforge.auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    // Used for ADMIN registration
    private String organizationName;

    // Used for both ADMIN & USER
    private String adminEmail;
    private String password;

    // Used ONLY for USER registration
    private String organizationId;
}
