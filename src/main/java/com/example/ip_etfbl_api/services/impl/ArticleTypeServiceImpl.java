package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.models.entities.ArticleTypeEntity;
import com.example.ip_etfbl_api.services.ArticleTypeService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ArticleTypeServiceImpl extends CrudJpaService<ArticleTypeEntity, Integer> implements ArticleTypeService {
    public ArticleTypeServiceImpl(JpaRepository<ArticleTypeEntity, Integer> repository, ModelMapper modelMapper) {
        super(repository, ArticleTypeEntity.class, modelMapper);
    }
}
