package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.models.requests.AuthRequest;
import com.example.ip_etfbl_api.models.requests.UserRegisterRequest;
import com.example.ip_etfbl_api.models.responses.AuthResponse;

public interface AuthenticationService {
    AuthResponse registerUser(UserRegisterRequest req);
    AuthResponse authenticateUser(AuthRequest req);

}
