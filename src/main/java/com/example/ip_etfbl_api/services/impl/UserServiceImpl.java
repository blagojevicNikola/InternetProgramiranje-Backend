package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.exceptions.BadRequestException;
import com.example.ip_etfbl_api.exceptions.ConflictException;
import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.entities.LocationEntity;
import com.example.ip_etfbl_api.models.entities.PersonEntity;
import com.example.ip_etfbl_api.models.entities.UserEntity;
import com.example.ip_etfbl_api.models.requests.UserInfoRequest;
import com.example.ip_etfbl_api.models.responses.UserInfo;
import com.example.ip_etfbl_api.repositories.LocationEntityRepository;
import com.example.ip_etfbl_api.repositories.PersonEntityRepository;
import com.example.ip_etfbl_api.repositories.UserEntityRepository;
import com.example.ip_etfbl_api.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends CrudJpaService<UserEntity, Integer> implements UserService {

    private final UserEntityRepository userEntityRepository;
    private final PersonEntityRepository personEntityRepository;
    private final LocationEntityRepository locationEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserEntityRepository repository, ModelMapper modelMapper, PersonEntityRepository personEntityRepository, LocationEntityRepository locationEntityRepository, PasswordEncoder passwordEncoder) {
        super(repository, UserEntity.class, modelMapper);
        this.userEntityRepository = repository;
        this.personEntityRepository = personEntityRepository;
        this.locationEntityRepository = locationEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public <T> T findByUsername(String username, Class<T> resultDto) {
        Optional<PersonEntity> user = personEntityRepository.findByUsername(username);
        if(user.isPresent())
        {
            return this.getModelMapper().map(user.get(), resultDto);
        }
        throw new NotFoundException();
    }

    @Override
    public UserInfo updateUser(UserInfoRequest request, String currentUsername) {
        if(!request.getUsername().equals(currentUsername))
        {
            if(personEntityRepository.existsPersonEntityByUsername(request.getUsername()))
            {
                System.out.println("Izlaz 1");
                throw new ConflictException();
            }
        }
        Optional<PersonEntity> person = personEntityRepository.findByUsername(currentUsername);
        person.ifPresentOrElse(m -> {
            m.setUsername(request.getUsername());
            m.setName(request.getName());
            m.setSurname(request.getSurname());
            UserEntity tmp = m.getUser();
            tmp.setPhoneNumber(request.getPhoneNumber());
            Optional<LocationEntity> loc = this.locationEntityRepository.findById(request.getUserLocationId());
            if(loc.isEmpty())
            {
                System.out.println("Izlaz 2");
                throw new ConflictException();
            }
            tmp.setLocation(loc.get());
        }, () -> {throw new NotFoundException();});
        PersonEntity response = this.personEntityRepository.save(person.get());
        return this.getModelMapper().map(response, UserInfo.class);
    }

    @Override
    public void updatePassword(String username, String currentPassword, String newPassword) {
        Optional<PersonEntity> person = this.personEntityRepository.findByUsername(username);
        if(person.isEmpty())
        {
            throw new NotFoundException();
        }

        String currentPasswordEnc = passwordEncoder.encode(currentPassword);
        String newPasswordEnc = passwordEncoder.encode(newPassword);
        if(newPassword.length()<8)
        {
            throw new BadRequestException();
        }
        if(!passwordEncoder.matches(currentPassword, person.get().getPassword()))
        {
            System.out.println(currentPasswordEnc);
            System.out.println(person.get().getPassword());
            throw new ConflictException();
        }
        person.get().setPassword(newPasswordEnc);
        this.personEntityRepository.save(person.get());
    }
}
