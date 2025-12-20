package com.flowforge.auth.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String email;
    private String password;
    private String organizationId;
}
