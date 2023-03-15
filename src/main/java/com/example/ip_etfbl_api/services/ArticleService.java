package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.base.CrudService;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;

import java.util.List;

public interface ArticleService extends CrudService<Integer> {

    <T> List<T> findAllByArticleTypeName(Class<T> resultDto, String name);
    ArticleInfo getArticleInfoById(Integer id);

    <T> List<T> findAllByDeletedAndSold(Class<T> resultDto, Boolean deleted, Boolean sold);

    <T> List<T> findAllByDeletedAndSoldAndUsername(Class<T> resultDto, Boolean deleted, Boolean sold, String username);
}
