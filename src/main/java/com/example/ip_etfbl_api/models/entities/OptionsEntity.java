package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "options", schema = "etfbl_ip", catalog = "")
@IdClass(OptionsEntityPK.class)
public class OptionsEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "value", nullable = false, length = 100)
    private String value;
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "structure_name", nullable = false)
    private String structureName;
    @ManyToOne
    //@JoinColumns({@JoinColumn(name = "structure_name", referencedColumnName = "name", nullable = false, insertable =  false, updatable = false), @JoinColumn(name = "structure_type_id", referencedColumnName = "article_type_id", nullable = false, insertable =  false, updatable = false)})
    @JoinColumns({@JoinColumn(name = "structure_name", referencedColumnName = "name", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "article_type_id", referencedColumnName = "article_type_id", nullable = false, insertable = false,updatable = false)})
    private AttributeStructureEntity attributeStructure;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "article_type_id", nullable = false)
    private Integer articleTypeId;


}
