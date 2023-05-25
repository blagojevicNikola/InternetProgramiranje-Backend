package com.example.ip_etfbl_api.models.requests;

import lombok.Data;

@Data
public class UserInfoRequest {
    private String name;
    private String username;
    private String surname;
    private String phoneNumber;
    private Integer locationId;
}
