package com.example.ip_etfbl_api.models.responses;

import lombok.Data;

@Data
public class UserInfo {
    private String name;
    private String username;
    private String surname;
    private String userPhoneNumber;
    private String userLocationName;
    private Integer userLocationId;
}
