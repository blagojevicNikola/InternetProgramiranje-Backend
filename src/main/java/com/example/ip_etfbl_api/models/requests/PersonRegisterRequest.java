package com.example.ip_etfbl_api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRegisterRequest {
    private String name;
    private String surname;
    private String password;
    private String username;
    private String role;
}
