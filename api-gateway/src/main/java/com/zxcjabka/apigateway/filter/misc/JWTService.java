package com.zxcjabka.apigateway.filter.misc;


public interface JWTService {
    boolean validateJWT(String jwt);

    Long extractId(String jwt);
}
