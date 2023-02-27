package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.base.CrudService;

import java.util.List;

public interface ArticleService extends CrudService<Integer> {

    public <T> List<T> findAllByArticleTypeName(Class<T> resultDto, String name);
}
