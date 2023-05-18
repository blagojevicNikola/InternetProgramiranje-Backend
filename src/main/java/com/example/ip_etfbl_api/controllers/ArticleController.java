package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.base.CrudController;
import com.example.ip_etfbl_api.models.requests.NewArticleRequest;
import com.example.ip_etfbl_api.models.responses.Article;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;
import com.example.ip_etfbl_api.services.ArticleService;
import com.example.ip_etfbl_api.services.PhotoService;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
//@CrossOrigin(origins = "http://localhost:4200", methods={RequestMethod.GET, RequestMethod.PUT})
public class ArticleController extends CrudController<Integer, Article, Article> {

    private final ArticleService service;
    private final PhotoService photoService;
    protected ArticleController(ArticleService service, PhotoService photoService) {
        super(Article.class, service);
        this.service = service;
        this.photoService = photoService;
    }

    @PostMapping()
    public ResponseEntity<ArticleInfo> addArticle(@RequestBody NewArticleRequest newArticle, Authentication authentication) throws IOException {
        List<String> photoUrls = this.photoService.savePhotos(newArticle.getPhotos());
        Optional<ArticleInfo> result = this.service.addArticle(newArticle, photoUrls);
        return result.map(articleInfo -> ResponseEntity.status(200).body(articleInfo)).orElseGet(() -> ResponseEntity.status(409).body(null));
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
