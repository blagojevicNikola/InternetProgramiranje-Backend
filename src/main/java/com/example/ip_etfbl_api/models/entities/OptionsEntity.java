package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "options", schema = "etfbl_ip", catalog = "")
@IdClass(OptionsEntityPK.class)
public class OptionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "value", nullable = false)
    private Integer value;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "structure_name", nullable = false)
    private Integer structureName;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "structure_type_id", nullable = false)
    private Integer structureTypeId;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "structure_name", referencedColumnName = "name", nullable = false, insertable =  false, updatable = false), @JoinColumn(name = "structure_type_id", referencedColumnName = "article_type_id", nullable = false, insertable =  false, updatable = false)})
    private AttributeStructureEntity attributeStructure;

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
        OptionsEntity that = (OptionsEntity) o;
        return Objects.equals(value, that.value) && Objects.equals(structureName, that.structureName) && Objects.equals(structureTypeId, that.structureTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, structureName, structureTypeId);
    }

    public AttributeStructureEntity getAttributeStructure() {
        return attributeStructure;
    }

    public void setAttributeStructure(AttributeStructureEntity attributeStructure) {
        this.attributeStructure = attributeStructure;
    }
}
