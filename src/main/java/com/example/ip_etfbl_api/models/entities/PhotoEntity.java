package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "photo", schema = "etfbl_ip", catalog = "")
public class PhotoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "url", nullable = false, length = 300)
    private String url;
    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
    private ArticleEntity article;

}
