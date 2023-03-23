package com.example.ip_etfbl_api.models.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Data
public class Comment {
    private Integer id;
    private String username;
    private String content;
    private Timestamp dateTime;

    public Comment(Integer id, String content, Timestamp dateTime, String username)
    {
        this.id = id;
        this.content = content;
        this.dateTime = dateTime;
        this.username = username;
    }
}
