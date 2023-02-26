package com.example.ip_etfbl_api.base;

import java.io.Serializable;

public interface BaseEntity<ID extends Serializable> {
    ID getId();
    void setId(ID id);
}
