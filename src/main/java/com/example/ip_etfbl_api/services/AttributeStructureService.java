package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.base.CrudService;

import java.util.List;

public interface AttributeStructureService extends CrudService<Integer> {
    <T> List<T> findAllByArticleTypeName(Class<T> resultDto, Integer name);
}
