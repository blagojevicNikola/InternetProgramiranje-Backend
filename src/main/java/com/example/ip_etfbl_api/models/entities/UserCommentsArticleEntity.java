package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

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
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false, insertable =  false, updatable = false)
    private ArticleEntity article;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "person_id", nullable = false, insertable =  false, updatable = false)
    private UserEntity user;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCommentsArticleEntity that = (UserCommentsArticleEntity) o;
        return Objects.equals(articleId, that.articleId) && Objects.equals(userId, that.userId) && Objects.equals(content, that.content) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, userId, content, dateTime);
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
