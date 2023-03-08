package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationEntityRepository extends JpaRepository<LocationEntity, Integer> {
    Optional<LocationEntity> findLocationEntityByName(String name);
}
