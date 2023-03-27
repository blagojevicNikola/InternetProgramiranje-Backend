package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.AttributeStructureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeStructureEntityRepository extends JpaRepository<AttributeStructureEntity, Integer> {
    List<AttributeStructureEntity> findAttributeStructureEntitiesByArticleTypeId(Integer name);
}
