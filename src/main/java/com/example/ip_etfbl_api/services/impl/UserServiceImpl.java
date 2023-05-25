package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.exceptions.ConflictException;
import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.entities.LocationEntity;
import com.example.ip_etfbl_api.models.entities.PersonEntity;
import com.example.ip_etfbl_api.models.entities.UserEntity;
import com.example.ip_etfbl_api.models.requests.UserInfoRequest;
import com.example.ip_etfbl_api.models.responses.User;
import com.example.ip_etfbl_api.models.responses.UserInfo;
import com.example.ip_etfbl_api.repositories.LocationEntityRepository;
import com.example.ip_etfbl_api.repositories.PersonEntityRepository;
import com.example.ip_etfbl_api.repositories.UserEntityRepository;
import com.example.ip_etfbl_api.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends CrudJpaService<UserEntity, Integer> implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final PersonEntityRepository personEntityRepository;
    private final LocationEntityRepository locationEntityRepository;

    public UserServiceImpl(UserEntityRepository repository, ModelMapper modelMapper, PersonEntityRepository personEntityRepository, LocationEntityRepository locationEntityRepository) {
        super(repository, UserEntity.class, modelMapper);
        this.userEntityRepository = repository;
        this.personEntityRepository = personEntityRepository;
        this.locationEntityRepository = locationEntityRepository;
    }

    @Override
    public User findByUsername(String username) {
        Optional<UserEntity> user = userEntityRepository.findUserEntityByPersonUsername(username);
        return user.map(userEntity -> new User(userEntity.getId(), userEntity.getPerson().getUsername(), userEntity.getLocation().getName())).orElse(null);
    }

    @Override
    public UserInfo updateUser(UserInfoRequest request, String currentUsername) {
        if(personEntityRepository.existsPersonEntityByUsername(request.getUsername()))
        {
            throw new ConflictException();
        }
        Optional<PersonEntity> person = personEntityRepository.findByUsername(currentUsername);
        person.ifPresentOrElse(m -> {
            m.setUsername(request.getUsername());
            m.setName(request.getName());
            m.setSurname(request.getSurname());
            UserEntity tmp = m.getUser();
            tmp.setPhoneNumber(request.getPhoneNumber());
            Optional<LocationEntity> loc = this.locationEntityRepository.findById(request.getLocationId());
            if(loc.isEmpty())
            {
                throw new ConflictException();
            }
            tmp.setLocation(loc.get());
        }, () -> {throw new NotFoundException();});
        PersonEntity response = this.personEntityRepository.save(person.get());
        return this.getModelMapper().map(response, UserInfo.class);
    }
}
