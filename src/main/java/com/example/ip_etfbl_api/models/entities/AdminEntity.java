package com.example.ip_etfbl_api.models.entities;

import com.example.ip_etfbl_api.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "admin", schema = "etfbl_ip", catalog = "")
public class AdminEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Basic
    @Column(name="surname", nullable=false, length = 100)
    private String surname;

    @Basic
    @Column(name="username", nullable=false, length=100, unique = true)
    private String username;

    @Basic
    @Column(name="password", nullable = false, length=100)
    private String password;

}
