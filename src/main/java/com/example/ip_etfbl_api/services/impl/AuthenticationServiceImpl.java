package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.exceptions.ConflictException;
import com.example.ip_etfbl_api.exceptions.ForbiddenException;
import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.entities.PersonEntity;
import com.example.ip_etfbl_api.models.entities.UserEntity;
import com.example.ip_etfbl_api.models.enums.Role;
import com.example.ip_etfbl_api.models.requests.AuthRequest;
import com.example.ip_etfbl_api.models.requests.UserRegisterRequest;
import com.example.ip_etfbl_api.models.responses.AuthResponse;
import com.example.ip_etfbl_api.repositories.AdminEntityRepository;
import com.example.ip_etfbl_api.repositories.LocationEntityRepository;
import com.example.ip_etfbl_api.repositories.PersonEntityRepository;
import com.example.ip_etfbl_api.repositories.UserEntityRepository;
import com.example.ip_etfbl_api.services.AuthenticationService;
import com.example.ip_etfbl_api.services.JwtService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PersonEntityRepository personEntityRepository;
    private final AdminEntityRepository adminEntityRepository;
    private final LocationEntityRepository locationEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender mailService;

    public AuthenticationServiceImpl(PersonEntityRepository personEntityRepository, UserEntityRepository userEntityRepository, AdminEntityRepository adminEntityRepository, LocationEntityRepository locationEntityRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, JavaMailSender mailService) {
        this.personEntityRepository = personEntityRepository;
        this.adminEntityRepository = adminEntityRepository;
        this.locationEntityRepository = locationEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.mailService = mailService;
    }

    @Override
    public Boolean registerUser(UserRegisterRequest req) throws MessagingException {
        if(personEntityRepository.existsPersonEntityByUsernameOrUserEmail(req.getUsername(), req.getEmail()))
        {
            throw new ConflictException();
        }
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
        user.setActivated(false);
        Random rand = new Random();
        user.setPin(rand.nextInt(8999)+1000);
        user.setEmail(req.getEmail());
        System.out.println(req.getCityName());
        user.setLocation(locationEntityRepository.findLocationEntityByName(req.getCityName()).get());
        //var savedUser = userEntityRepository.saveAndFlush(user);
        person.setUser(user);
        var savedPerson = personEntityRepository.save(person);
        MimeMessage mail = formMessage(savedPerson.getUser().getEmail(),
                savedPerson.getUser().getPin(), "Profile activation");
        mailService.send(mail);
        return true;
    }

    @Override
    public AuthResponse authenticateUser(AuthRequest req) throws MessagingException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        var user = personEntityRepository.findByUsername(req.getUsername()).orElseThrow();
        if(user.getUser().getActivated())
        {
            var jwtToken = jwtService.generateToken(user);
            return new AuthResponse(jwtToken, true);
        }else
        {
            Random rand = new Random();
            user.getUser().setPin(rand.nextInt(8999)+1000);
            var updated = personEntityRepository.save(user);
            MimeMessage message = formMessage(updated.getUser().getEmail(), updated.getUser().getPin(), "Activate profile");
            mailService.send(message);
            return new AuthResponse(null, false);
        }
    }

    @Override
    public Boolean authenticateAdmin(AuthRequest req) {
        if(adminEntityRepository.existsAdminEntityByUsernameAndPassword(req.getUsername(), req.getPassword()))
        {
            return true;
        }
        throw new ForbiddenException();
    }

    @Override
    public AuthResponse activateProfile(Integer pin, String username) {
        var user = personEntityRepository.findByUsernameAndDeletedAndUserPin(username, false, pin);
        if(user.isEmpty())
        {
            throw new NotFoundException();
        }else
        {
            user.get().getUser().setActivated(true);
            var updatedUser = personEntityRepository.save(user.get());
            var jwtToken = jwtService.generateToken(updatedUser);
            return new AuthResponse(jwtToken, true);
        }
    }

    private MimeMessage formMessage(String to, Integer pin, String subject) throws MessagingException {
        MimeMessage mail = mailService.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("This is your PIN code for profile activation: " +
                pin);
        return mail;
    }

}
