package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.entities.MessageEntity;
import com.example.ip_etfbl_api.models.entities.UserEntity;
import com.example.ip_etfbl_api.models.responses.Message;
import com.example.ip_etfbl_api.repositories.MessageEntityRepository;
import com.example.ip_etfbl_api.repositories.UserEntityRepository;
import com.example.ip_etfbl_api.services.MessageService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageEntityRepository messageEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(MessageEntityRepository messageEntityRepository, UserEntityRepository userEntityRepository, ModelMapper modelMapper) {
        this.messageEntityRepository = messageEntityRepository;
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Message> getMessagesFromUser(String username) {
        return this.messageEntityRepository.findMessageEntitiesByUserPersonUsernameOrderByDateTimeAsc(username).stream()
                .map(m -> this.modelMapper.map(m, Message.class)).collect(Collectors.toList());
    }

    @Override
    public Message sendMessage(String content, String username) {
        Optional<UserEntity> user = this.userEntityRepository.findUserEntityByPersonUsername(username);
        if(user.isEmpty())
        {
            throw new NotFoundException();
        }
        MessageEntity newMsg = new MessageEntity();
        newMsg.setContent(content);
        newMsg.setUserId(user.get().getId());
        newMsg.setUser(user.get());
        newMsg.setSeen(false);
        newMsg.setDateTime(Timestamp.from(Instant.now()));
        MessageEntity result = this.messageEntityRepository.save(newMsg);
        return this.modelMapper.map(result, Message.class);
    }
}
