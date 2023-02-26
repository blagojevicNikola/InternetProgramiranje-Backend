package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class AttributeStructureEntityPK implements Serializable {

    private Integer name;

    private Integer articleTypeId;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getArticleTypeId() {
        return articleTypeId;
    }

    public void setArticleTypeId(Integer articleTypeId) {
        this.articleTypeId = articleTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeStructureEntityPK that = (AttributeStructureEntityPK) o;
        return Objects.equals(name, that.name) && Objects.equals(articleTypeId, that.articleTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, articleTypeId);
    }
}
