package com.example.ip_etfbl_api.models.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ArticleInfo {
    private Integer id;
    private String title;
    private String details;
    private BigDecimal price;
    private Timestamp date;
    private Boolean isNew;
    private Integer articleTypeId;
    private String articleTypeName;
    private User user;
    private Boolean sold;
    private List<Comment> comments;
    private List<Attribute> attributes;
    private List<Photo> photos;

}
