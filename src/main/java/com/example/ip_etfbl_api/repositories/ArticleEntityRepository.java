package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, Integer> {
    List<ArticleEntity> findArticleEntitiesByArticleTypeNameAndDeleted(String name, Boolean deleted);
    List<ArticleEntity> findArticleEntitiesByUserLocationId(Integer id);
    List<ArticleEntity> findArticleEntitiesByDeletedAndSold(Boolean deleted, Boolean sold);
}
