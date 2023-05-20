package com.example.ip_etfbl_api;

import com.example.ip_etfbl_api.converters.ArticleEntityToArticleConverter;
import com.example.ip_etfbl_api.models.entities.ArticleEntity;
import com.example.ip_etfbl_api.models.responses.Article;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IpEtfblApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpEtfblApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Converter<ArticleEntity, Article> converter = new ArticleEntityToArticleConverter();
        modelMapper.addConverter(converter);
        return modelMapper;

    }
}
