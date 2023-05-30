package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.models.responses.Option;

import java.util.List;

public interface OptionsService {
    List<Option> getAllOptionsByArticleTypeName(String name);
}
