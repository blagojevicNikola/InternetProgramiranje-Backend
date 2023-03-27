package com.example.ip_etfbl_api.models.entities;

import com.example.ip_etfbl_api.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "attribute_structure", schema = "etfbl_ip", catalog = "")
@IdClass(AttributeStructureEntityPK.class)
public class AttributeStructureEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "name", nullable = false, length=45)
    private String name;
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

    public AttributeStructureEntity() {
    }

    @Override
    public Integer getId() {
        return this.articleTypeId;
    }

    @Override
    public void setId(Integer pk) {
        this.articleTypeId = pk;
        //this.name = pk.getName();
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AttributeStructureEntity;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AttributeStructureEntity)) return false;
        final AttributeStructureEntity other = (AttributeStructureEntity) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$articleTypeId = this.getArticleTypeId();
        final Object other$articleTypeId = other.getArticleTypeId();
        if (this$articleTypeId == null ? other$articleTypeId != null : !this$articleTypeId.equals(other$articleTypeId))
            return false;
        final Object this$valueType = this.getValueType();
        final Object other$valueType = other.getValueType();
        if (this$valueType == null ? other$valueType != null : !this$valueType.equals(other$valueType)) return false;
        final Object this$multivalue = this.getMultivalue();
        final Object other$multivalue = other.getMultivalue();
        if (this$multivalue == null ? other$multivalue != null : !this$multivalue.equals(other$multivalue))
            return false;
        final Object this$articleType = this.getArticleType();
        final Object other$articleType = other.getArticleType();
        if (this$articleType == null ? other$articleType != null : !this$articleType.equals(other$articleType))
            return false;
        final Object this$options = this.getOptions();
        final Object other$options = other.getOptions();
        if (this$options == null ? other$options != null : !this$options.equals(other$options)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $articleTypeId = this.getArticleTypeId();
        result = result * PRIME + ($articleTypeId == null ? 43 : $articleTypeId.hashCode());
        final Object $valueType = this.getValueType();
        result = result * PRIME + ($valueType == null ? 43 : $valueType.hashCode());
        final Object $multivalue = this.getMultivalue();
        result = result * PRIME + ($multivalue == null ? 43 : $multivalue.hashCode());
        /*final Object $articleType = this.getArticleType();
        result = result * PRIME + ($articleType == null ? 43 : $articleType.hashCode());*/
        final Object $options = this.getOptions();
        result = result * PRIME + ($options == null ? 43 : $options.hashCode());
        return result;
    }

}
