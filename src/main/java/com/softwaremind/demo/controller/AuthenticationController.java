package com.softwaremind.demo.controller;

import com.softwaremind.demo.dto.security.AuthenticationRequest;
import com.softwaremind.demo.dto.security.AuthenticationResponse;
import com.softwaremind.demo.dto.security.RegisterRequest;
import com.softwaremind.demo.service.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Users can register through /api/auth/register
//Sign in though /api/auth/authenticate
//After sign-in they receive JWT token
//Token supposed to be attached in header Authorization: Bearer {token} for further requests
//endpoints are secured according to User's role
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

}
