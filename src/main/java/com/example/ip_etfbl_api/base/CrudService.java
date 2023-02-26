package com.example.ip_etfbl_api.base;

import java.io.Serializable;
import java.util.List;

public interface CrudService<ID extends Serializable> {
    <T> List<T> findAll(Class<T> resultDto);
    <T> T findById(ID id, Class<T> resultDto);
    <T,U> T insert(U object, Class<T> resultDto);
    <T,U> T update(ID id, U object, Class<T> resultDto);
    void delete(ID id);
}
