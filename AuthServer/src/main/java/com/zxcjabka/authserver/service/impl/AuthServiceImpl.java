package com.zxcjabka.authserver.service.impl;

import com.zxcjabka.authserver.presistence.entity.UserEntity;
import com.zxcjabka.authserver.presistence.repository.UserRepository;
import com.zxcjabka.authserver.service.AuthService;
import com.zxcjabka.authserver.service.JWTService;
import com.zxcjabka.authserver.service.dto.AuthRequest;
import com.zxcjabka.authserver.service.dto.AuthResponse;
import com.zxcjabka.authserver.service.dto.RegistrationRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(JWTService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        UserEntity userEntity = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(authRequest.getUsername()));
        if (!passwordEncoder.matches(authRequest.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        String token = jwtService.createJWT(userEntity);
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse register(RegistrationRequest registrationRequest) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(registrationRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        newUser.setEmail(registrationRequest.getEmail());
        newUser = userRepository.saveAndFlush(newUser);
        String token = jwtService.createJWT(newUser);
        return new AuthResponse(token);
    }
}
