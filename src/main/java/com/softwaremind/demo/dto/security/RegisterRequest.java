package com.softwaremind.demo.dto.security;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
