package com.example.ip_etfbl_api.models.entities;

import com.example.ip_etfbl_api.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "article", schema = "etfbl_ip", catalog = "")
public class ArticleEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "title", nullable = false, length = 45)
    private String title;
    @Basic
    @Column(name = "details", nullable = true, length = 45)
    private String details;
    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    private BigDecimal price;
    @Basic
    @Column(name = "is_new", nullable = false)
    private Boolean isNew;
    @Basic
    @Column(name = "sold", nullable = false)
    private Boolean sold;
    @Basic
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Basic
    @Column(name="date", nullable=false)
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "person_id", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private ArticleTypeEntity articleType;
    @OneToMany(mappedBy = "article", cascade=CascadeType.PERSIST)
    private List<AttributeEntity> attributes;
    @OneToMany(mappedBy = "article")
    private List<UserCommentsArticleEntity> userComments;
    @OneToMany(mappedBy = "article", cascade = CascadeType.PERSIST)
    private List<PhotoEntity> photos;
    @OneToMany(mappedBy = "article")
    private List<UserCommentsArticleEntity> userCommentsArticles;

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

}
