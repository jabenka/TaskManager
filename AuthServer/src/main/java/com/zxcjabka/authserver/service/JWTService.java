package com.zxcjabka.authserver.service;

import com.zxcjabka.authserver.presistence.entity.UserEntity;

public interface JWTService {

    public String createJWT(UserEntity userEntity);
    public boolean validateJWT(String jwt);
}
