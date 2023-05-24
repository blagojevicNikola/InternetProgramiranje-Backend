package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.entities.*;
import com.example.ip_etfbl_api.models.requests.AttributeRequest;
import com.example.ip_etfbl_api.models.requests.NewArticleRequest;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;
import com.example.ip_etfbl_api.models.responses.Comment;
import com.example.ip_etfbl_api.models.responses.User;
import com.example.ip_etfbl_api.repositories.*;
import com.example.ip_etfbl_api.services.ArticleService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl extends CrudJpaService<ArticleEntity, Integer> implements ArticleService {

    private final ArticleEntityRepository articleEntityRepository;
    private final ArticleTypeRepository articleTypeRepository;
    private final UserEntityRepository userEntityRepository;
    private final PhotoEntityRepository photoEntityRepository;
    private final AttributeEntityRepository attributeEntityRepository;
    private final UserCommentsArticleEntityRepository userCommentsArticleEntityRepository;
    public ArticleServiceImpl(ModelMapper modelMapper, ArticleEntityRepository articleEntityRepository, ArticleTypeRepository articleTypeRepository, UserEntityRepository userEntityRepository, PhotoEntityRepository photoEntityRepository, AttributeEntityRepository attributeEntityRepository, UserCommentsArticleEntityRepository userCommentsArticleEntityRepository) {
        super(articleEntityRepository, ArticleEntity.class , modelMapper);
        this.articleEntityRepository = articleEntityRepository;
        this.articleTypeRepository = articleTypeRepository;
        this.userEntityRepository = userEntityRepository;
        this.photoEntityRepository = photoEntityRepository;
        this.attributeEntityRepository = attributeEntityRepository;
        this.userCommentsArticleEntityRepository = userCommentsArticleEntityRepository;
    }
    @Override
    public <T> Page<T> findAllByArticleTypeName(Class<T> resultDto, String name, int pageNo, int pageSize)
    {
        Page<ArticleEntity> tempSlice = articleEntityRepository.findArticleEntitiesByArticleTypeNameAndDeleted(name, false, PageRequest.of(pageNo,pageSize));
        return tempSlice.map(m -> this.getModelMapper().map(m, resultDto));
    }

    @Override
    public ArticleInfo getArticleInfoById(Integer id) throws NotFoundException {
        Optional<ArticleEntity> article = articleEntityRepository.findById(id);
        if(article.isEmpty())
            throw new NotFoundException();
        ArticleEntity articleTmp = article.get();
        Optional<UserEntity> user = userEntityRepository.findById(article.get().getUser().getId());
        if(user.isEmpty())
            throw new NotFoundException();
        UserEntity userTmp = user.get();
        List<UserCommentsArticleEntity> comments = userCommentsArticleEntityRepository.findUserCommentsArticleEntitiesByArticleIdAndUserId(articleTmp.getId(), userTmp.getId());
        ArticleInfo result = getModelMapper().map(articleTmp, ArticleInfo.class);
        result.setUser(new User(userTmp.getId(), userTmp.getPerson().getUsername(), userTmp.getLocation().getName()));
        result.setComments(comments.stream().map(m -> getModelMapper().map(m, Comment.class)).collect(Collectors.toList()));
        return result;
    }

    @Override
    public Optional<ArticleInfo> addArticle(NewArticleRequest request, List<String> photoUrls, String username) {
        Optional<ArticleTypeEntity> type = this.articleTypeRepository.findById(request.getCategoryId());
        if(type.isEmpty())
            return Optional.empty();
        Optional<UserEntity> user = this.userEntityRepository.findUserEntityByPersonUsername(username);
        if(user.isEmpty())
            return Optional.empty();
        ArticleEntity newArticle = new ArticleEntity();
        newArticle.setUser(user.get());
        newArticle.setTitle(request.getTitle());
        newArticle.setIsNew(request.getIsNew());
        newArticle.setDate(Timestamp.from(Instant.now()));
        newArticle.setDeleted(false);
        newArticle.setSold(false);
        newArticle.setPrice(request.getPrice());
        newArticle.setArticleType(type.get());
        newArticle.setDetails(request.getDetails());
        this.setAttributesInArticle(newArticle, request.getAttributes());
        this.setPhotosInArticle(newArticle, photoUrls);
        ArticleEntity e = this.articleEntityRepository.save(newArticle);
        return Optional.of(this.getModelMapper().map(e, ArticleInfo.class));
    }

    @Override
    public <T> Page<T> findAllByDeletedAndSold(Class<T> resultDto, Boolean deleted, Boolean sold, int pageNo, int pageSize) {
        Page<ArticleEntity> tempSlice = articleEntityRepository.findArticleEntitiesByDeletedAndSold(deleted,sold, PageRequest.of(pageNo, pageSize));
        return tempSlice.map(m -> this.getModelMapper().map(m, resultDto));
    }

    @Override
    public Boolean softDelete(Integer id, String username) {
        ArticleEntity toBeDeleted =  articleEntityRepository.findArticleEntityByIdAndDeletedAndUserPersonUsername(id, false, username);
        if(toBeDeleted == null)
            return false;
        toBeDeleted.setDeleted(true);
        articleEntityRepository.save(toBeDeleted);
        return true;
    }

    @Override
    public Optional<ArticleInfo> updateArticle(Integer id, NewArticleRequest request, List<String> photos, String username) {
        Optional<ArticleTypeEntity> type = this.articleTypeRepository.findById(request.getCategoryId());
        if(type.isEmpty())
            return Optional.empty();
        ArticleEntity toBeUpdated = articleEntityRepository.findArticleEntityByIdAndDeletedAndUserPersonUsername(id, false, username);
        if(toBeUpdated==null)
        {
            return Optional.empty();
        }
        toBeUpdated.setTitle(request.getTitle());
        toBeUpdated.setPrice(request.getPrice());
        toBeUpdated.setDetails(request.getDetails());
        toBeUpdated.setNew(request.getIsNew());
        toBeUpdated.setArticleType(type.get());
        this.updateAttributes(request.getAttributes(), toBeUpdated);
        this.deleteMissingPhotos(toBeUpdated,photos);
        this.setPhotosInArticle(toBeUpdated, photos);
        ArticleEntity e = this.articleEntityRepository.save(toBeUpdated);
        return Optional.of(this.getModelMapper().map(e, ArticleInfo.class));
    }


    @Override
    public <T> Page<T> findAllByDeletedAndSoldAndUsername(Class<T> resultDto, Boolean deleted, Boolean sold, String username, int pageNo, int pageSize) {
        Page<ArticleEntity> tempSlice = articleEntityRepository.findArticleEntitiesByDeletedAndSoldAndUserPersonUsername(deleted, sold, username, PageRequest.of(pageNo, pageSize));
        return tempSlice.map(m -> this.getModelMapper().map(m, resultDto));
    }

    private void setAttributesInArticle(ArticleEntity article, List<AttributeRequest> attributesRequest)
    {
        List<AttributeEntity> attributes = new ArrayList<>();
        if(attributesRequest!=null)
        {
            for(AttributeRequest a : attributesRequest)
            {
                AttributeEntity tmp = new AttributeEntity();
                tmp.setId(a.getId());
                tmp.setValue(a.getValue());
                tmp.setName(a.getName());
                tmp.setArticle(article);
                attributes.add(tmp);
            }
        }
        article.setAttributes(attributes);
    }

    private void setPhotosInArticle(ArticleEntity article, List<String> photoUrls)
    {
        List<PhotoEntity> photos = new ArrayList<>();
        if(photoUrls!=null)
        {
            for (String photoUrl : photoUrls) {
                PhotoEntity tmp = new PhotoEntity();
                tmp.setArticle(article);
                tmp.setUrl(photoUrl);
                photos.add(tmp);
            }
        }
        article.setPhotos(photos);
    }

    private void deleteMissingPhotos(ArticleEntity article, List<String> photos)
    {
        List<PhotoEntity> toDelete = article.getPhotos().stream().filter(a -> !photos.contains(a.getUrl())).toList();
        toDelete.forEach(t -> {
            article.getPhotos().remove(t);
            this.photoEntityRepository.deleteById(t.getId());
        });
    }

    private void updateAttributes(List<AttributeRequest> attributes, ArticleEntity article)
    {
        List<AttributeEntity> toAdd = article.getAttributes();
        toAdd.forEach(old -> {
            if(attributes.stream().noneMatch(nev -> old.getName().equals(nev.getName())))
            {
                toAdd.remove(old);
                this.attributeEntityRepository.deleteAttributeEntityByArticleIdAndName(article.getId(), old.getName());
            }
        });
        attributes.forEach(s ->{
           Optional<AttributeEntity> tmp = article.getAttributes().stream().filter(a -> s.getName().equals(a.getName())).findFirst();
           tmp.ifPresentOrElse(m -> m.setValue(s.getValue()),
                   () -> {
               AttributeEntity tmpAttribute = new AttributeEntity();
               tmpAttribute.setName(s.getName());
               tmpAttribute.setValue(s.getValue());
               tmpAttribute.setArticle(article);
               toAdd.add(tmpAttribute);
           });
        });
        article.setAttributes(toAdd);
    }

}
