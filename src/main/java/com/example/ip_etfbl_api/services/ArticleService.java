package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.base.CrudService;
import com.example.ip_etfbl_api.models.requests.NewArticleRequest;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArticleService extends CrudService<Integer> {

    <T> Page<T> findAllByArticleTypeName(Class<T> resultDto, String name, int pageNo, int pageSize);
    ArticleInfo getArticleInfoById(Integer id);
    Optional<ArticleInfo> addArticle(NewArticleRequest request, List<String> photoUrls, String username);
    <T> Page<T> findAllByDeletedAndSold(Class<T> resultDto, Boolean deleted, Boolean sold, int pageNo, int pageSize);
    Boolean softDelete(Integer id, String username);
    Optional<ArticleInfo> updateArticle(Integer id, NewArticleRequest request, List<String> photos, String username);
    <T> Page<T> findAllActiveArticlesByAttributes(Class<T> resultDto, Map<String, String> params, int pageNo, int pageSize, Sort sort);
    <T> Page<T> findAllActiveArticlesByTypeAndAttributes(Class<T> resultDto, Map<String, String> params, String typeName, int pageNo, int pageSize, Sort sort);
    <T> Page<T> findArticlesWithQueries(Class<T> resultDto, Map<String, String> params, Integer typeId, int pageNo, int pageSize, Sort sort);
    <T> Page<T> findAllByDeletedAndSoldAndUsername(Class<T> resultDto, Boolean deleted, Boolean sold, String username, int pageNo, int pageSize);
}
