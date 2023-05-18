package com.example.ip_etfbl_api.models.requests;

import com.example.ip_etfbl_api.models.entities.AttributeEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewArticleRequest {
    private String title;
    private BigDecimal price;
    private String details;
    private Integer categoryId;
    private List<AttributeRequest> attributes;
    private Boolean isNew;
    private List<MultipartFile> photos;
}