package com.softwaremind.demo.dto.security;

import com.softwaremind.demo.model.security.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
