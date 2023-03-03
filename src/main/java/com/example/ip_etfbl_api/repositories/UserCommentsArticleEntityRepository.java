package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.UserCommentsArticleEntity;
import com.example.ip_etfbl_api.models.entities.UserCommentsArticleEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCommentsArticleEntityRepository extends JpaRepository<UserCommentsArticleEntity, UserCommentsArticleEntityPK> {
    public List<UserCommentsArticleEntity> findUserCommentsArticleEntitiesByArticleIdAndUserId(Integer articleId, Integer userId);
}
