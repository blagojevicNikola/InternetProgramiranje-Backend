package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.models.entities.AttributeStructureEntity;
import com.example.ip_etfbl_api.repositories.AttributeStructureEntityRepository;
import com.example.ip_etfbl_api.services.AttributeStructureService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttributeStructureServiceImpl extends CrudJpaService<AttributeStructureEntity, Integer> implements AttributeStructureService {

    private final AttributeStructureEntityRepository attributeStructureEntityRepository;

    public AttributeStructureServiceImpl(ModelMapper modelMapper, AttributeStructureEntityRepository attributeStructureEntityRepository) {
        super(attributeStructureEntityRepository,AttributeStructureEntity.class, modelMapper);
        this.attributeStructureEntityRepository = attributeStructureEntityRepository;
    }

    @Override
    public <T> List<T> findAllByArticleTypeName( Class<T> resultDto, Integer name) {
        /*List<AttributeStructureEntity> list = this.attributeStructureEntityRepository.findAttributeStructureEntitiesByArticleTypeId(name);
        System.out.println(list);*/

        return this.attributeStructureEntityRepository.findAttributeStructureEntitiesByArticleTypeId(name)
                .stream().map(m -> this.getModelMapper().map(m, resultDto)).collect(Collectors.toList());

        //return new ArrayList<T>();
    }
}
