package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_comments_article", schema = "etfbl_ip", catalog = "")
@IdClass(UserCommentsArticleEntityPK.class)
public class UserCommentsArticleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "article_id", nullable = false)
    private Integer articleId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Basic
    @Column(name = "content", nullable = false, length = 500)
    private String content;
    @Basic
    @Column(name = "date_time", nullable = false)
    private Timestamp dateTime;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)})
    private ArticleEntity article;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "user_id", referencedColumnName = "person_id", nullable = false, insertable = false, updatable = false)})
    private UserEntity user;

}
