package com.example.ip_etfbl_api.models.responses;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Comment {
    private String userPersonUsername;
    private String content;
    private Timestamp dateTime;

    public Comment(Integer id, String content, Timestamp dateTime, String username)
    {
        this.content = content;
        this.dateTime = dateTime;
        this.userPersonUsername = username;
    }
}
