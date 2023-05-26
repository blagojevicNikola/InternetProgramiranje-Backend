package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.models.responses.Message;
import com.example.ip_etfbl_api.repositories.MessageEntityRepository;
import com.example.ip_etfbl_api.services.MessageService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageEntityRepository messageEntityRepository;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(MessageEntityRepository messageEntityRepository, ModelMapper modelMapper) {
        this.messageEntityRepository = messageEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Message> getMessagesFromUser(String username) {
        return this.messageEntityRepository.findMessageEntitiesByUserPersonUsernameOrderByDateTimeAsc(username).stream()
                .map(m -> this.modelMapper.map(m, Message.class)).collect(Collectors.toList());
    }
}
