package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, Integer> {
    Page<ArticleEntity> findArticleEntitiesByArticleTypeNameAndDeleted(String name, Boolean deleted, Pageable pageable);
    List<ArticleEntity> findArticleEntitiesByUserLocationId(Integer id);
    ArticleEntity findArticleEntityByIdAndDeletedAndUserPersonUsername(Integer id, Boolean deleted, String username);
    Page<ArticleEntity> findArticleEntitiesByDeletedAndSold(Boolean deleted, Boolean sold, Pageable pageable);
    Page<ArticleEntity> findArticleEntitiesByDeletedAndSoldAndUserPersonUsername(Boolean deleted, Boolean sold, String username, Pageable pageable);
    @Query("SELECT DISTINCT u FROM ArticleEntity u JOIN u.attributes attr WHERE u.deleted=:deleted and u.sold=:sold and u.articleType.name=:typeName and (:locationId is null or u.user.location.id=:locationId) and (:search is null or u.title LIKE %:search%) and (:priceFrom is null or u.price >= :priceFrom) and (:priceTo is null or u.price <= :priceTo) and ((:namesSize = 0) or attr.name IN :names) and ((:valuesSize = 0) or attr.value IN :values) group by u.id having ((:namesSize=0) or count(u) = :namesSize)")
    Page<ArticleEntity> findArticleEntitiesWithQuery(Boolean deleted, Boolean sold, Integer locationId, String search, BigDecimal priceFrom, BigDecimal priceTo, String typeName, @Param("names") List<String> names, @Param("values") List<String> values, int namesSize, int valuesSize, Pageable pageable);

}
