package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.base.CrudController;
import com.example.ip_etfbl_api.models.responses.Article;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;
import com.example.ip_etfbl_api.services.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
//@CrossOrigin(origins = "http://localhost:4200", methods={RequestMethod.GET, RequestMethod.PUT})
public class ArticleController extends CrudController<Integer, Article, Article> {

    private final ArticleService service;
    protected ArticleController(ArticleService service) {
        super(Article.class, service);
        this.service = service;
    }

    @GetMapping("/type/{name}")
    public Slice<Article> getArticlesByArticleTypeName(@PathVariable String name, @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                       @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize)
    {
        return service.findAllByArticleTypeName(Article.class, name, pageNo, pageSize);
    }

    @GetMapping("/active/user/{name}")
    public Slice<Article> getActiveArticlesByUser(@PathVariable String name, @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                  @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize)
    {
        return service.findAllByDeletedAndSoldAndUsername(Article.class, false, false, name,pageNo, pageSize);
    }

    @GetMapping("/sold/user/{name}")
    public Slice<Article> getSoldArticlesByUser(@PathVariable String name, @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize)
    {
        return service.findAllByDeletedAndSoldAndUsername(Article.class, false, true, name, pageNo, pageSize);
    }

    @GetMapping("/info/{id}")
    public ArticleInfo getArticleInfoById(@PathVariable Integer id)
    {
        return service.getArticleInfoById(id);
    }

    @GetMapping("/all")
    public Slice<Article> getAllArticles(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                          @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize)
    {
        return service.findAllByDeletedAndSold(Article.class, false, false, pageNo, pageSize);
    }
}
