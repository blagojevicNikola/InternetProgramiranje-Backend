package com.example.ip_etfbl_api.models.requests;

import com.example.ip_etfbl_api.models.responses.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest{
    private String name;
    private String surname;
    private String password;
    private String username;
    private String role;
    private String email;
    private Integer avatar;
    private String cityName;
}
