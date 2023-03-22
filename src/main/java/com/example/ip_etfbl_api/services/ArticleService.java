package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.base.CrudService;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ArticleService extends CrudService<Integer> {

    <T> Slice<T> findAllByArticleTypeName(Class<T> resultDto, String name, int pageNo, int pageSize);
    ArticleInfo getArticleInfoById(Integer id);

    <T> Slice<T> findAllByDeletedAndSold(Class<T> resultDto, Boolean deleted, Boolean sold, int pageNo, int pageSize);

    <T> Slice<T> findAllByDeletedAndSoldAndUsername(Class<T> resultDto, Boolean deleted, Boolean sold, String username, int pageNo, int pageSize);
}
