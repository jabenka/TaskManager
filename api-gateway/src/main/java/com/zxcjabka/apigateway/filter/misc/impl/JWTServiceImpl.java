package com.zxcjabka.apigateway.filter.misc.impl;

import com.zxcjabka.apigateway.filter.misc.JWTService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JWTServiceImpl implements JWTService {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public boolean validateJWT(String jwt) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (JwtException e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Long extractId(String jwt) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        String userid = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody().getSubject();
        return Long.parseLong(userid);
    }
}
