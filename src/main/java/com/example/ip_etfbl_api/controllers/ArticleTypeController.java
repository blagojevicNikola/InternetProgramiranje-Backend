package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.base.CrudController;
import com.example.ip_etfbl_api.base.CrudService;
import com.example.ip_etfbl_api.models.responses.ArticleType;
import com.example.ip_etfbl_api.models.responses.Option;
import com.example.ip_etfbl_api.services.ArticleTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article-types")
//@CrossOrigin(origins = "http://localhost:4200", methods={RequestMethod.GET, RequestMethod.PUT})
public class ArticleTypeController extends CrudController<Integer, ArticleType, ArticleType> {

    private final ArticleTypeService service;
    public ArticleTypeController(ArticleTypeService service) {
        super(ArticleType.class, service);
        this.service = service;
    }

    @GetMapping("/{name}/options")
    public List<Option> getOptionsOfArticleType(@PathVariable String name)
    {
        return this.service.getOptionsOfArticleType(name);
    }

}
