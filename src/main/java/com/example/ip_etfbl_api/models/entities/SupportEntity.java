package com.example.ip_etfbl_api.models.entities;

import com.example.ip_etfbl_api.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "support", schema = "etfbl_ip", catalog = "")
public class SupportEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "person_id", nullable = false)
    private Integer personId;
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
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
