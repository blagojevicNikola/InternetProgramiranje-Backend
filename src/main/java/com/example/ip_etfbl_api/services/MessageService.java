package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.models.responses.Message;

import java.util.List;

public interface MessageService{
    List<Message> getMessagesFromUser(String username);
}
