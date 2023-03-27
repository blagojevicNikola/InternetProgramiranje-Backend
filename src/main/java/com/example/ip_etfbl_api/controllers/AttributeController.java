package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.models.responses.AttributeStructure;
import com.example.ip_etfbl_api.services.AttributeStructureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attribute")
public class AttributeController {
    private final AttributeStructureService attributeStructureService;

    public AttributeController(AttributeStructureService attributeStructureService) {
        this.attributeStructureService = attributeStructureService;
    }

    @GetMapping("/structure/{name}")
    public List<AttributeStructure> getAllAttributeStructures(@PathVariable Integer name)
    {
        return this.attributeStructureService.findAllByArticleTypeName(AttributeStructure.class, name);
    }
}
