package com.example.ip_etfbl_api.models.entities;

import com.example.ip_etfbl_api.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "user", schema = "etfbl_ip", catalog = "")
public class UserEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "person_id", nullable = false)
    private Integer personId;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "avatar", nullable = true)
    private Integer avatar;
    @Basic
    @Column(name = "activated", nullable = false)
    private Boolean activated;
    @Basic
    @Column(name = "pin", nullable = true)
    private Integer pin;
    @OneToMany(mappedBy = "user")
    private List<ArticleEntity> articles;
    @OneToMany(mappedBy = "user")
    private List<MessageEntity> messages;
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private PersonEntity person;
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private LocationEntity location;
    @OneToMany(mappedBy = "user")
    private List<UserCommentsArticleEntity> userComments;

    @Override
    public Integer getId() {
        return personId;
    }

    @Override
    public void setId(Integer integer) {
        personId = integer;
    }

}
