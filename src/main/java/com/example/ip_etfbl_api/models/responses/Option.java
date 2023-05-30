package com.example.ip_etfbl_api.models.responses;

import lombok.Data;

import java.util.List;

@Data
public class Option {
    private String viewName;
    private List<String> content;
    private Boolean multivalue;
}
