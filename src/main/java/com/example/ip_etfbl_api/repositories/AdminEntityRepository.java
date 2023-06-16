package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminEntityRepository extends JpaRepository<AdminEntity, Integer> {
    Boolean existsAdminEntityByUsernameAndPassword(String username, String password);
}
