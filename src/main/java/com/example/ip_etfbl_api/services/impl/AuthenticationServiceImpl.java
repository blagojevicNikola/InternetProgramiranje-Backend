package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.models.entities.PersonEntity;
import com.example.ip_etfbl_api.models.entities.UserEntity;
import com.example.ip_etfbl_api.models.enums.Role;
import com.example.ip_etfbl_api.models.requests.AuthRequest;
import com.example.ip_etfbl_api.models.requests.UserRegisterRequest;
import com.example.ip_etfbl_api.models.responses.AuthResponse;
import com.example.ip_etfbl_api.repositories.LocationEntityRepository;
import com.example.ip_etfbl_api.repositories.PersonEntityRepository;
import com.example.ip_etfbl_api.repositories.UserEntityRepository;
import com.example.ip_etfbl_api.services.AuthenticationService;
import com.example.ip_etfbl_api.services.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PersonEntityRepository personEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final LocationEntityRepository locationEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(PersonEntityRepository personEntityRepository, UserEntityRepository userEntityRepository, LocationEntityRepository locationEntityRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.personEntityRepository = personEntityRepository;
        this.userEntityRepository = userEntityRepository;
        this.locationEntityRepository = locationEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse registerUser(UserRegisterRequest req) {
        var person = new PersonEntity();
        person.setName(req.getName());
        person.setSurname(req.getSurname());
        person.setUsername(req.getUsername());
        person.setPassword(passwordEncoder.encode(req.getPassword()));
        person.setRole(Role.USER);
        person.setDeleted(false);
        //var savedPerson = personEntityRepository.saveAndFlush(person);
        var user = new UserEntity();
        user.setPerson(person);
        user.setActivated(true);
        user.setPin(3333);
        user.setEmail(req.getEmail());
        System.out.println(req.getCityName());
        user.setLocation(locationEntityRepository.findLocationEntityByName(req.getCityName()).get());
        //var savedUser = userEntityRepository.saveAndFlush(user);
        person.setUser(user);
        var savedPerson = personEntityRepository.save(person);
        var jwtToken = jwtService.generateToken(savedPerson);
        return new AuthResponse(jwtToken);
    }

    @Override
    public AuthResponse authenticateUser(AuthRequest req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        var user = personEntityRepository.findByUsername(req.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }
}
