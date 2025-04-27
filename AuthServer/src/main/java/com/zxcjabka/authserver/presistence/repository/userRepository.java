package com.zxcjabka.authserver.presistence.repository;

import com.zxcjabka.authserver.presistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<UserEntity, Long> {

     boolean existsById(Long Id);

     Optional<UserEntity> findByUsername(String username);
}
