package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "attribute_structure", schema = "etfbl_ip", catalog = "")
@IdClass(AttributeStructureEntityPK.class)
public class AttributeStructureEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "name", nullable = false)
    private Integer name;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "article_type_id", nullable = false)
    private Integer articleTypeId;
    @Basic
    @Column(name = "value_type", nullable = false, length = 45)
    private String valueType;
    @Basic
    @Column(name = "multivalue", nullable = false)
    private Boolean multivalue;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "article_type_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)})
    private ArticleTypeEntity articleType;
    @OneToMany(mappedBy = "attributeStructure")
    private List<OptionsEntity> options;

}
