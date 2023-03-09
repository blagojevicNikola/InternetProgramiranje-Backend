package com.example.ip_etfbl_api.models.entities;

import com.example.ip_etfbl_api.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "support", schema = "etfbl_ip", catalog = "")
public class SupportEntity implements BaseEntity<Integer> {
    @Id
    @Column(name = "person_id")
    private Integer personId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity person;

    @Override
    public Integer getId() {
        return personId;
    }

    @Override
    public void setId(Integer integer) {
        personId = integer;
    }

}
