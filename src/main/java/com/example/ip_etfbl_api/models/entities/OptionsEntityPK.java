package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Data
public class OptionsEntityPK implements Serializable {

    @Column(name = "value", nullable = false, length = 100)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String value;

    @Column(name = "structure_name", nullable = false)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String structureName;
    @Column(name = "article_type_id", nullable = false)
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleTypeId;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionsEntityPK that = (OptionsEntityPK) o;
        return Objects.equals(value, that.value) && Objects.equals(structureName, that.structureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, structureName);
    }

    public Integer getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(Integer articleTypeId) {
        this.articleTypeId = articleTypeId;
    }
}
