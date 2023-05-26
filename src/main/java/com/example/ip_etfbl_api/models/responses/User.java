package com.example.ip_etfbl_api.models.responses;

import lombok.*;


@Data
public class User {
    private Integer id;
    private String username;
    private String userLocationName;

    public User()
    {}
    public User(Integer id, String username, String locationName)
    {
        this.id = id;
        this.username = username;
        this.userLocationName = locationName;
    }
}
