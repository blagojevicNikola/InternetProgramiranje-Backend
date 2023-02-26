package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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
    @JoinColumn(name = "article_type_id", referencedColumnName = "id", nullable = false, insertable =  false, updatable = false)
    private ArticleTypeEntity articleType;
    @OneToMany(mappedBy = "attributeStructure")
    private List<OptionsEntity> options;

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

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Boolean getMultivalue() {
        return multivalue;
    }

    public void setMultivalue(Boolean multivalue) {
        this.multivalue = multivalue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeStructureEntity that = (AttributeStructureEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(articleTypeId, that.articleTypeId) && Objects.equals(valueType, that.valueType) && Objects.equals(multivalue, that.multivalue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, articleTypeId, valueType, multivalue);
    }

    public ArticleTypeEntity getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleTypeEntity articleType) {
        this.articleType = articleType;
    }

    public List<OptionsEntity> getOptions() {
        return options;
    }

    public void setOptions(List<OptionsEntity> options) {
        this.options = options;
    }
}
