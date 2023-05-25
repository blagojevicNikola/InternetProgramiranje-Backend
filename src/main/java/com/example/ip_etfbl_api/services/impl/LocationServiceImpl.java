package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.entities.LocationEntity;
import com.example.ip_etfbl_api.repositories.LocationEntityRepository;
import com.example.ip_etfbl_api.services.LocationService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationServiceImpl extends CrudJpaService<LocationEntity, Integer> implements LocationService {

    public LocationServiceImpl(LocationEntityRepository repository, ModelMapper modelMapper) {
        super(repository, LocationEntity.class, modelMapper);
    }
}
