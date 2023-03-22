package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.ArticleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, Integer> {
    Slice<ArticleEntity> findArticleEntitiesByArticleTypeNameAndDeleted(String name, Boolean deleted, Pageable pageable);
    List<ArticleEntity> findArticleEntitiesByUserLocationId(Integer id);
    Slice<ArticleEntity> findArticleEntitiesByDeletedAndSold(Boolean deleted, Boolean sold, Pageable pageable);
    Slice<ArticleEntity> findArticleEntitiesByDeletedAndSoldAndUserPersonUsername(Boolean deleted, Boolean sold, String username, Pageable pageable);
}
