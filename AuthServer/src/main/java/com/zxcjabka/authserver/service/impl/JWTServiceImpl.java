package com.zxcjabka.authserver.service.impl;

import com.zxcjabka.authserver.presistence.entity.UserEntity;
import com.zxcjabka.authserver.service.JWTService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.zxcjabka.authserver.presistence.repository.userRepository;

import java.nio.charset.StandardCharsets;
import java.util.Date;
@Service
public class JWTServiceImpl implements JWTService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expiration;

    private final userRepository userRepository;

    public JWTServiceImpl(com.zxcjabka.authserver.presistence.repository.userRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public String createJWT(UserEntity userEntity) {
        return Jwts.builder()
                .setSubject(userEntity.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    @Override
    public boolean validateJWT(String jwt) {
        String userid = Jwts.parser().build().parseClaimsJws(jwt).getBody().getSubject();
        Long userId= Long.valueOf(userid);
        return userRepository.existsById(userId);

    }
}
