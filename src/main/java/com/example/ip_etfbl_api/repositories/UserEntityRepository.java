package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserEntityByPersonUsername(String username);
}
