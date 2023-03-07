package com.example.ip_etfbl_api.models.entities;

import com.example.ip_etfbl_api.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "article_type", schema = "etfbl_ip", catalog = "")
public class ArticleTypeEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @OneToMany(mappedBy = "articleType")
    private List<ArticleEntity> articles;
    @OneToMany(mappedBy = "articleType")
    private List<AttributeStructureEntity> attributeStructure;
    @OneToMany(mappedBy = "articleType")
    private List<AttributeStructureEntity> attributeStructures;

}
