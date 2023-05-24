package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AttributeEntityRepository extends JpaRepository<AttributeEntity, Integer> {

    void deleteAttributeEntityByArticleIdAndName(Integer articleId, String name);
}
