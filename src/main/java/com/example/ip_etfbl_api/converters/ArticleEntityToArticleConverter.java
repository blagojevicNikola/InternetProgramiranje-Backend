package com.example.ip_etfbl_api.converters;

import com.example.ip_etfbl_api.models.entities.ArticleEntity;
import com.example.ip_etfbl_api.models.responses.Article;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class ArticleEntityToArticleConverter implements Converter<ArticleEntity, Article> {
    @Override
    public Article convert(MappingContext<ArticleEntity, Article> mappingContext) {
        ArticleEntity source = mappingContext.getSource();
        Article destination = mappingContext.getDestination();

        if(destination==null)
        {
            destination = new Article();
        }

        destination.setId(source.getId());
        destination.setTitle(source.getTitle());
        destination.setPrice(source.getPrice());
        destination.setDetails(source.getDetails());
        destination.setIsNew(source.getIsNew());

        if(source.getPhotos()!=null && !source.getPhotos().isEmpty())
        {
            destination.setPhotoUrl(source.getPhotos().get(0).getUrl());
        }

        return destination;
    }
}
