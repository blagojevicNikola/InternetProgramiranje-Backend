package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.models.entities.PersonEntity;
import com.example.ip_etfbl_api.repositories.PersonEntityRepository;
import com.example.ip_etfbl_api.services.JwtUserService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class JwtUserServiceImpl implements JwtUserService {

    private final PersonEntityRepository personEntityRepository;

    public JwtUserServiceImpl(PersonEntityRepository personEntityRepository) {
        this.personEntityRepository = personEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PersonEntity> person = personEntityRepository.findByUsername(username);
        if(person.isEmpty())
        {
            throw new UsernameNotFoundException("User doesn't exist!");
        }
        return person.get();
    }
}
