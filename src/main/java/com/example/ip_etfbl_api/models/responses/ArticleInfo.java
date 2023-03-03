package com.example.ip_etfbl_api.models.responses;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ArticleInfo {
    private Integer id;
    private String title;
    private String details;
    private BigDecimal price;
    private Boolean isNew;
    private User user;
    private List<Comment> comments;
}
