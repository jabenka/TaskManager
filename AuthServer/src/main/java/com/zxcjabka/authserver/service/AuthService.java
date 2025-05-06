package com.zxcjabka.authserver.service;

import com.zxcjabka.authserver.service.dto.AuthRequest;
import com.zxcjabka.authserver.service.dto.AuthResponse;
import com.zxcjabka.authserver.service.dto.RegistrationRequest;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
    AuthResponse register(RegistrationRequest registrationRequest);
}
