package com.example.ip_etfbl_api.models.entities;

import com.example.ip_etfbl_api.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "person", schema = "etfbl_ip", catalog = "")
public class PersonEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    private String surname;
    @OneToOne(mappedBy = "person")
    private AdminEntity admin;
    @OneToOne(mappedBy = "person")
    private SupportEntity support;
    @OneToOne(mappedBy = "person")
    private UserEntity user;

}
