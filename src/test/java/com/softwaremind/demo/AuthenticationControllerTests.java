package com.softwaremind.demo;

import com.softwaremind.demo.controller.AuthenticationController;
import com.softwaremind.demo.dto.security.AuthenticationRequest;
import com.softwaremind.demo.dto.security.AuthenticationResponse;
import com.softwaremind.demo.dto.security.RegisterRequest;
import com.softwaremind.demo.service.security.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthenticationControllerTests {
    @InjectMocks
    private AuthenticationController authenticationController;
    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void register_shouldCallService() {
        RegisterRequest request = new RegisterRequest();
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token("test-token")
                .build();
        when(authenticationService.register(any(RegisterRequest.class))).thenReturn(response);

        ResponseEntity<AuthenticationResponse> result = authenticationController.register(request);

        assertEquals(ResponseEntity.ok(response), result);
        verify(authenticationService, times(1)).register(request);
    }

    @Test
    public void authenticate_shouldCallService() {
        AuthenticationRequest request = new AuthenticationRequest();
        AuthenticationResponse response = AuthenticationResponse.builder()
                .token("test-token")
                .build();
        when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(response);

        ResponseEntity<AuthenticationResponse> result = authenticationController.authenticate(request);

        assertEquals(ResponseEntity.ok(response), result);
        verify(authenticationService, times(1)).authenticate(request);
    }
}
