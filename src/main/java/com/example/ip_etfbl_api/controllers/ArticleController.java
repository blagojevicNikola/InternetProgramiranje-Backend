package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.base.CrudController;
import com.example.ip_etfbl_api.models.requests.NewArticleRequest;
import com.example.ip_etfbl_api.models.responses.Article;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;
import com.example.ip_etfbl_api.services.ArticleService;
import com.example.ip_etfbl_api.services.PhotoService;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ArticleInfo> addArticle(@RequestPart NewArticleRequest newArticle, @RequestPart Optional<List<MultipartFile>> photos, Authentication authentication) throws IOException {
        List<String> photoUrls = null;
        if (photos.isPresent()) {
            photoUrls = this.photoService.savePhotos(photos.get());
        }
        Optional<ArticleInfo> result = this.service.addArticle(newArticle, photoUrls, authentication.getName());
        return result.map(articleInfo -> ResponseEntity.status(200).body(articleInfo)).orElseGet(() -> ResponseEntity.status(409).body(null));
    }


    @GetMapping("/type/{name}")
    public Slice<Article> getArticlesByArticleTypeName(@PathVariable String name, @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                       @RequestParam(value = "pageSize", defaultValue = "8", required = false) int pageSize,
                                                       @RequestParam Map<String,String> allParams,
                                                       @RequestParam(value="sort", required = false)String sort) {
        allParams.remove("pageNo");
        allParams.remove("pageSize");
        allParams.remove("sort");
        Sort sortOrder = this.getArticleSortOrder(sort);
        return this.service.findAllActiveArticlesByTypeAndAttributes(Article.class, allParams, name, pageNo, pageSize, sortOrder);
    }

    @GetMapping("/just-test/{name}")
    public Slice<Article> test(@PathVariable String name, @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                     @RequestParam(value = "pageSize", defaultValue = "8", required = false) int pageSize,
                                     @RequestParam Map<String,String> allParams, @RequestParam(name="sort", required = false) String sort)
    {

        System.out.println(allParams);
        System.out.println(sort);
        return null;
    }

    @GetMapping("/active/user/{name}")
    public Slice<Article> getActiveArticlesByUser(@PathVariable String name, @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                  @RequestParam(value = "pageSize", defaultValue = "8", required = false) int pageSize) {
        return service.findAllByDeletedAndSoldAndUsername(Article.class, false, false, name, pageNo, pageSize);
    }

    @GetMapping("/sold/user/{name}")
    public Slice<Article> getSoldArticlesByUser(@PathVariable String name, @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                @RequestParam(value = "pageSize", defaultValue = "8", required = false) int pageSize) {
        return service.findAllByDeletedAndSoldAndUsername(Article.class, false, true, name, pageNo, pageSize);
    }

    @GetMapping("/info/{id}")
    public ArticleInfo getArticleInfoById(@PathVariable Integer id) {
        return service.getArticleInfoById(id);
    }

    @GetMapping("/all")
    public Slice<Article> getAllArticles(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize) {
        //return service.findAllByDeletedAndSold(Article.class, false, false, pageNo, pageSize
        return this.service.findAllByDeletedAndSold(Article.class, false, false, pageNo, pageSize);
    }

    @GetMapping("/search")
    public Slice<Article> searchArticles(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize,
                                         @RequestParam(value = "category", required = false) String category,
                                         @RequestParam Map<String,String> allParams,
                                         @RequestParam(value="sort", required = false)String sort)
    {
        allParams.remove("pageNo");
        allParams.remove("pageSize");
        allParams.remove("sort");
        allParams.remove("category");
        Sort sortOrder = this.getArticleSortOrder(sort);
        return this.service.findArticlesWithQueries(Article.class, allParams,category,pageNo, pageSize, sortOrder);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") int id, Authentication authentication) {
        String username = authentication.getName();
        if (service.softDelete(id, username)) {
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.status(409).body(null);
    }

    //Using POST instead of PUT request is necessary in this case.
    //When using PUT, Spring Boot throw's an exception saying it doesn't support application/octet-stream Content Type
    //Tomcat configuration is probably causing that behaviour
    @PostMapping(value="/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ArticleInfo> updateArticle(@PathVariable("id") int id,
                                                     @RequestPart NewArticleRequest newArticle,
                                                     @RequestPart Optional<List<MultipartFile>> newPhotos,
                                                     @RequestPart Optional<List<String>> existingPhotos,
                                                     Authentication authentication) throws IOException {
        String username = authentication.getName();
        List<String> photos = this.getImagesForUpdate(newPhotos, existingPhotos);
        Optional<ArticleInfo> result = service.updateArticle(id, newArticle, photos, username);
        if(result.isPresent())
        {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.status(409).body(null);
    }

    @PutMapping("/buy/{id}")
    public ResponseEntity<Void> buyAnArticle(@PathVariable Integer id, Authentication authentication)
    {
        String username = authentication.getName();
        if(this.service.buyAnArticle(id, username))
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(409).build();
    }


    private List<String> getImagesForUpdate(Optional<List<MultipartFile>> newPhotos, Optional<List<String>> existingPhotos) throws IOException {
        List<String> result = new ArrayList<>();
        if(newPhotos.isPresent())
        {
            result.addAll(photoService.savePhotos(newPhotos.get()));
        }
        existingPhotos.ifPresent(result::addAll);
        return result;
    }

    private Sort getArticleSortOrder(String sort)
    {
        Sort defaultSort = Sort.by(Sort.Order.desc("date"));
        if(sort==null)
        {
            return defaultSort;
        }
        if(!sort.matches("^(date|price),(asc|desc)$"))
        {
            return defaultSort;
        }
        String[] data = sort.split(",");
        if(data[1].equals("desc"))
        {
            return Sort.by(Sort.Order.desc(data[0]));
        }
        else {
            return Sort.by(Sort.Order.asc(data[0]));
        }
    }
}
