package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "message", schema = "etfbl_ip", catalog = "")
@IdClass(MessageEntityPK.class)
public class MessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "content", nullable = false, length = 500)
    private String content;
    @Basic
    @Column(name = "seen", nullable = false)
    private Boolean seen;
    @Basic
    @Column(name = "date_time", nullable = false)
    private Timestamp dateTime;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "user_id", referencedColumnName = "person_id", nullable = false, insertable = false, updatable = false)})
    private UserEntity user;

}
