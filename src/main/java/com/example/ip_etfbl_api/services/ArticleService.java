package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.base.CrudService;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;

import java.util.List;

public interface ArticleService extends CrudService<Integer> {

    public <T> List<T> findAllByArticleTypeName(Class<T> resultDto, String name);
    public ArticleInfo getArticleInfoById(Integer id);
}
