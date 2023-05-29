package com.example.ip_etfbl_api.models.responses;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Article {
    private Integer id;
    private String title;
    private BigDecimal price;
    private String details;
    private Boolean isNew;
    private Boolean sold;
    private String photoUrl;
    private Timestamp date;
}
