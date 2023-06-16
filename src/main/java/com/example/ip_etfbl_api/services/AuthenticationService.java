package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.models.requests.AuthRequest;
import com.example.ip_etfbl_api.models.requests.UserRegisterRequest;
import com.example.ip_etfbl_api.models.responses.AuthResponse;
import jakarta.mail.MessagingException;

public interface AuthenticationService {
    Boolean registerUser(UserRegisterRequest req) throws MessagingException;
    AuthResponse authenticateUser(AuthRequest req) throws MessagingException;
    Boolean authenticateAdmin(AuthRequest req);

    AuthResponse activateProfile(Integer pin, String username);

}
