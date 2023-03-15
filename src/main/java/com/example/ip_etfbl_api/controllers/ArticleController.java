package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.base.CrudController;
import com.example.ip_etfbl_api.models.responses.Article;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;
import com.example.ip_etfbl_api.services.ArticleService;
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
    public List<Article> getArticlesByArticleTypeName(@PathVariable String name)
    {
        return service.findAllByArticleTypeName(Article.class, name);
    }

    @GetMapping("/active/user/{name}")
    public List<Article> getActiveArticlesByUser(@PathVariable String name)
    {
        return service.findAllByDeletedAndSoldAndUsername(Article.class, false, false, name);
    }

    @GetMapping("/sold/user/{name}")
    public List<Article> getSoldArticlesByUser(@PathVariable String name)
    {
        return service.findAllByDeletedAndSoldAndUsername(Article.class, false, true, name);
    }

    @GetMapping("/info/{id}")
    public ArticleInfo getArticleInfoById(@PathVariable Integer id)
    {
        return service.getArticleInfoById(id);
    }

    @GetMapping("/all")
    public List<Article> getAllArticles()
    {
        return service.findAllByDeletedAndSold(Article.class, false, false);
    }
}
