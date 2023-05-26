package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.models.responses.Message;
import com.example.ip_etfbl_api.services.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/user")
    public List<Message> getMessagesOfUser(Authentication authentication)
    {
        return this.messageService.getMessagesFromUser(authentication.getName());
    }

}
