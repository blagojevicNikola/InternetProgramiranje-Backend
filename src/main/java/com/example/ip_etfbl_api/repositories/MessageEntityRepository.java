package com.example.ip_etfbl_api.repositories;

import com.example.ip_etfbl_api.models.entities.MessageEntity;
import com.example.ip_etfbl_api.models.entities.MessageEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageEntityRepository extends JpaRepository<MessageEntity, MessageEntityPK> {
    List<MessageEntity> findMessageEntitiesByUserPersonUsernameOrderByDateTimeAsc(String username);
}
