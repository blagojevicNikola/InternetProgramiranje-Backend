package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.models.responses.Option;
import com.example.ip_etfbl_api.repositories.OptionsEntityRepository;
import com.example.ip_etfbl_api.services.OptionsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OptionsServiceImpl implements OptionsService {
    private final OptionsEntityRepository optionsEntityRepository;

    public OptionsServiceImpl(OptionsEntityRepository optionsEntityRepository) {
        this.optionsEntityRepository = optionsEntityRepository;
    }

    @Override
    public List<Option> getAllOptionsByArticleTypeName(String name) {
    return null;
    }
}
