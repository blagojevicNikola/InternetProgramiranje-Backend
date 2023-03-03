package com.example.ip_etfbl_api.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
public class UserCommentsArticleEntityPK implements Serializable {

    private Integer articleId;

    private Integer userId;

}
