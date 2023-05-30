package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.base.CrudService;
import com.example.ip_etfbl_api.models.responses.Option;

import java.util.List;

public interface ArticleTypeService extends CrudService<Integer> {
    List<Option> getOptionsOfArticleType(String name);
}
