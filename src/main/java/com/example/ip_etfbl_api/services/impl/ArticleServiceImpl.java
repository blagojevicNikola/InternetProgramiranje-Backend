package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.entities.*;
import com.example.ip_etfbl_api.models.requests.NewArticleRequest;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;
import com.example.ip_etfbl_api.models.responses.Comment;
import com.example.ip_etfbl_api.models.responses.User;
import com.example.ip_etfbl_api.repositories.ArticleEntityRepository;
import com.example.ip_etfbl_api.repositories.ArticleTypeRepository;
import com.example.ip_etfbl_api.repositories.UserCommentsArticleEntityRepository;
import com.example.ip_etfbl_api.repositories.UserEntityRepository;
import com.example.ip_etfbl_api.services.ArticleService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
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
    private final UserCommentsArticleEntityRepository userCommentsArticleEntityRepository;
    public ArticleServiceImpl(ModelMapper modelMapper, ArticleEntityRepository articleEntityRepository, ArticleTypeRepository articleTypeRepository, UserEntityRepository userEntityRepository, UserCommentsArticleEntityRepository userCommentsArticleEntityRepository) {
        super(articleEntityRepository, ArticleEntity.class , modelMapper);
        this.articleEntityRepository = articleEntityRepository;
        this.articleTypeRepository = articleTypeRepository;
        this.userEntityRepository = userEntityRepository;
        this.userCommentsArticleEntityRepository = userCommentsArticleEntityRepository;
    }
    @Override
    public <T> Slice<T> findAllByArticleTypeName(Class<T> resultDto, String name, int pageNo, int pageSize)
    {
        Slice<ArticleEntity> tempSlice = articleEntityRepository.findArticleEntitiesByArticleTypeNameAndDeleted(name, false, PageRequest.of(pageNo,pageSize));
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
        List<PhotoEntity> photos = new ArrayList<>();
        for (String photoUrl : photoUrls) {
            PhotoEntity tmp = new PhotoEntity();
            tmp.setUrl(photoUrl);
        }
        newArticle.setPhotos(photos);
        ArticleEntity e = this.articleEntityRepository.save(newArticle);
        return Optional.of(this.getModelMapper().map(e, ArticleInfo.class));
    }

    @Override
    public <T> Slice<T> findAllByDeletedAndSold(Class<T> resultDto, Boolean deleted, Boolean sold, int pageNo, int pageSize) {
        Slice<ArticleEntity> tempSlice = articleEntityRepository.findArticleEntitiesByDeletedAndSold(deleted,sold, PageRequest.of(pageNo, pageSize));
        return tempSlice.map(m -> this.getModelMapper().map(m, resultDto));
    }

    @Override
    public <T> Slice<T> findAllByDeletedAndSoldAndUsername(Class<T> resultDto, Boolean deleted, Boolean sold, String username, int pageNo, int pageSize) {
        Slice<ArticleEntity> tempSlice = articleEntityRepository.findArticleEntitiesByDeletedAndSoldAndUserPersonUsername(deleted, sold, username, PageRequest.of(pageNo, pageSize));
        return tempSlice.map(m -> this.getModelMapper().map(m, resultDto));
    }
}
