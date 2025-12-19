package com.flowforge.auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String organizationName;
    private String adminEmail;
    private String password;
}
