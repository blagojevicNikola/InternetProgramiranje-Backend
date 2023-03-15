package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.entities.UserEntity;
import com.example.ip_etfbl_api.models.responses.User;
import com.example.ip_etfbl_api.repositories.UserEntityRepository;
import com.example.ip_etfbl_api.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends CrudJpaService<UserEntity, Integer> implements UserService {

    private final UserEntityRepository userEntityRepository;

    public UserServiceImpl(UserEntityRepository repository, ModelMapper modelMapper) {
        super(repository, UserEntity.class, modelMapper);
        this.userEntityRepository = repository;
    }

    @Override
    public User findByUsername(String username) {
        Optional<UserEntity> user = userEntityRepository.findUserEntityByPersonUsername(username);
        return user.map(userEntity -> new User(userEntity.getId(), userEntity.getPerson().getUsername(), userEntity.getLocation().getName())).orElse(null);
    }
}
