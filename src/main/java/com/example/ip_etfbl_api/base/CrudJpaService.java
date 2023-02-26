package com.example.ip_etfbl_api.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Getter
public class CrudJpaService<E extends BaseEntity<ID>, ID extends Serializable> implements CrudService<ID> {

    private final JpaRepository<E, ID> repository;
    private final Class<E> entityClass;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public CrudJpaService(JpaRepository<E, ID> repository, Class<E> entityClass, ModelMapper modelMapper) {
        this.repository = repository;
        this.entityClass = entityClass;
        this.modelMapper = modelMapper;
    }


    @Override
    public <T> List<T> findAll(Class<T> resultDto) {
        return repository.findAll().stream().map(m -> modelMapper.map(m, resultDto)).collect(Collectors.toList());
    }

    @Override
    public <T> T findById(ID id, Class<T> resultDto) {
        return modelMapper.map(findEntityById(id), resultDto);
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDto) {
        E entity = modelMapper.map(object, entityClass);
        entity.setId(null);
        entity = repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity, resultDto);
    }

    @Override
    public <T, U> T update(ID id, U object, Class<T> resultDto) {
        E entity = modelMapper.map(object, entityClass);
        entity.setId(id);
        entity = repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity, resultDto);
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }

    public E findEntityById(ID id) {return repository.findById(id).orElse(null);};
}
