package com.example.ip_etfbl_api.models.entities;

import com.example.ip_etfbl_api.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "admin", schema = "etfbl_ip", catalog = "")
public class AdminEntity implements BaseEntity<Integer> {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "person_id", nullable = false)
    private Integer personId;
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private PersonEntity person;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    @Override
    public Integer getId() {
        return personId;
    }

    @Override
    public void setId(Integer integer) {
        personId=integer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminEntity that = (AdminEntity) o;
        return Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}
