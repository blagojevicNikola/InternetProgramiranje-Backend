package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.entities.ArticleTypeEntity;
import com.example.ip_etfbl_api.models.entities.OptionsEntity;
import com.example.ip_etfbl_api.models.responses.Option;
import com.example.ip_etfbl_api.repositories.ArticleTypeRepository;
import com.example.ip_etfbl_api.services.ArticleTypeService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleTypeServiceImpl extends CrudJpaService<ArticleTypeEntity, Integer> implements ArticleTypeService {

    private final ArticleTypeRepository repository;
    public ArticleTypeServiceImpl(ArticleTypeRepository repository, ModelMapper modelMapper) {
        super(repository, ArticleTypeEntity.class, modelMapper);
        this.repository=repository;
    }


    @Override
    public List<Option> getOptionsOfArticleType(String name) {
        Optional<ArticleTypeEntity> artType = this.repository.findArticleTypeEntityByName(name);
        if(artType.isEmpty())
        {
            throw new NotFoundException();
        }
        List<Option> result = new ArrayList<>();
        artType.get().getAttributeStructure().stream().forEach(a -> {
            Option o = new Option();
            o.setViewName(a.getName());
            o.setMultivalue(a.getMultivalue());
            o.setContent(a.getOptions().stream().map(OptionsEntity::getValue).collect(Collectors.toList()));
            result.add(o);
        });
        return result;
    }
}
