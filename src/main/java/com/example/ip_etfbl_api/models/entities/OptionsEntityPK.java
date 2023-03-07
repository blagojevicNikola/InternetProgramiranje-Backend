package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class OptionsEntityPK implements Serializable {

    @Column(name = "value", nullable = false, length = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer value;

    @Column(name = "structure_name", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer structureName;

    private Integer structureTypeId;
    @Column(name = "article_type_id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleTypeId;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getStructureName() {
        return structureName;
    }

    public void setStructureName(Integer structureName) {
        this.structureName = structureName;
    }

    public Integer getStructureTypeId() {
        return structureTypeId;
    }

    public void setStructureTypeId(Integer structureTypeId) {
        this.structureTypeId = structureTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionsEntityPK that = (OptionsEntityPK) o;
        return Objects.equals(value, that.value) && Objects.equals(structureName, that.structureName) && Objects.equals(structureTypeId, that.structureTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, structureName, structureTypeId);
    }

    public Integer getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(Integer articleTypeId) {
        this.articleTypeId = articleTypeId;
    }
}
