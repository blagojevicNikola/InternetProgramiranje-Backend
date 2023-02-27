package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.repositories.ArticleEntityRepository;
import com.example.ip_etfbl_api.services.ArticleService;
import com.example.ip_etfbl_api.models.entities.ArticleEntity;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl extends CrudJpaService<ArticleEntity, Integer> implements ArticleService {

    private final ArticleEntityRepository articleEntityRepository;
    public ArticleServiceImpl(ModelMapper modelMapper, ArticleEntityRepository articleEntityRepository) {
        super(articleEntityRepository, ArticleEntity.class , modelMapper);
        this.articleEntityRepository = articleEntityRepository;
    }
    @Override
    public <T> List<T> findAllByArticleTypeName(Class<T> resultDto, String name)
    {
        return articleEntityRepository.findArticleEntitiesByArticleTypeNameAndDeleted(name, false).stream().map(m -> this.getModelMapper().map(m,resultDto)).collect(Collectors.toList());
    }
}
