package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.ArticleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleTypeRepository extends JpaRepository<ArticleTypeEntity, Integer> {
    Optional<ArticleTypeEntity> findArticleTypeEntityByName(String name);
}
