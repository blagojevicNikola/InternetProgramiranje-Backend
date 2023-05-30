package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.OptionsEntity;
import com.example.ip_etfbl_api.models.entities.OptionsEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionsEntityRepository extends JpaRepository<OptionsEntity, OptionsEntityPK> {
    List<OptionsEntity> findOptionsEntitiesByAttributeStructureArticleTypeName(String name);
}
