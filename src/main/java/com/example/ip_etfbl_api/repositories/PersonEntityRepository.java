package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonEntityRepository extends JpaRepository<PersonEntity, Integer> {
    Optional<PersonEntity> findByUsername(String username);
}
