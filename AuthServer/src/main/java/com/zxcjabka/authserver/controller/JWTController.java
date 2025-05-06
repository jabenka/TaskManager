package com.zxcjabka.authserver.controller;

import com.zxcjabka.authserver.service.AuthService;
import com.zxcjabka.authserver.service.dto.AuthRequest;
import com.zxcjabka.authserver.service.dto.RegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class JWTController {

    private final AuthService authService;

    public JWTController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }


}
