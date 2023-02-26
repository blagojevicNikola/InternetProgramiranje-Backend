package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.ArticleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTypeRepository extends JpaRepository<ArticleTypeEntity, Integer> {
}
