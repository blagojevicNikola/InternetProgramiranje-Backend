package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.models.requests.AuthRequest;
import com.example.ip_etfbl_api.models.requests.UserRegisterRequest;
import com.example.ip_etfbl_api.models.responses.AuthResponse;
import com.example.ip_etfbl_api.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register-user")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserRegisterRequest req)
    {
        return ResponseEntity.ok(service.registerUser(req));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest req)
    {
        return ResponseEntity.ok(service.authenticateUser(req));
    }


}
