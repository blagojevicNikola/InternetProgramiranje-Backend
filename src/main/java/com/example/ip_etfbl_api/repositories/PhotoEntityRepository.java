package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhotoEntityRepository extends JpaRepository<PhotoEntity, Integer> {
    public void deleteByUrl(String url);
    public List<PhotoEntity> findPhotoEntitiesByArticleId(Integer id);
}
