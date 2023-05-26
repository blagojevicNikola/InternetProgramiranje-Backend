package com.example.ip_etfbl_api.models.responses;

import lombok.Data;

@Data
public class Message {
    public String userUsername;
    public String dateTime;
    public String content;
    public Boolean seen;

}
