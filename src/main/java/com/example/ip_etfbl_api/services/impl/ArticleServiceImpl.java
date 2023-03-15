package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.base.CrudJpaService;
import com.example.ip_etfbl_api.exceptions.NotFoundException;
import com.example.ip_etfbl_api.models.entities.UserCommentsArticleEntity;
import com.example.ip_etfbl_api.models.entities.UserCommentsArticleEntityPK;
import com.example.ip_etfbl_api.models.entities.UserEntity;
import com.example.ip_etfbl_api.models.responses.ArticleInfo;
import com.example.ip_etfbl_api.models.responses.Comment;
import com.example.ip_etfbl_api.models.responses.User;
import com.example.ip_etfbl_api.repositories.ArticleEntityRepository;
import com.example.ip_etfbl_api.repositories.UserCommentsArticleEntityRepository;
import com.example.ip_etfbl_api.repositories.UserEntityRepository;
import com.example.ip_etfbl_api.services.ArticleService;
import com.example.ip_etfbl_api.models.entities.ArticleEntity;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl extends CrudJpaService<ArticleEntity, Integer> implements ArticleService {

    private final ArticleEntityRepository articleEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final UserCommentsArticleEntityRepository userCommentsArticleEntityRepository;
    public ArticleServiceImpl(ModelMapper modelMapper, ArticleEntityRepository articleEntityRepository, UserEntityRepository userEntityRepository, UserCommentsArticleEntityRepository userCommentsArticleEntityRepository) {
        super(articleEntityRepository, ArticleEntity.class , modelMapper);
        this.articleEntityRepository = articleEntityRepository;
        this.userEntityRepository = userEntityRepository;
        this.userCommentsArticleEntityRepository = userCommentsArticleEntityRepository;
    }
    @Override
    public <T> List<T> findAllByArticleTypeName(Class<T> resultDto, String name)
    {
        return articleEntityRepository.findArticleEntitiesByArticleTypeNameAndDeleted(name, false).stream().map(m -> this.getModelMapper().map(m,resultDto)).collect(Collectors.toList());
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
    public <T> List<T> findAllByDeletedAndSold(Class<T> resultDto, Boolean deleted, Boolean sold) {
        return articleEntityRepository.findArticleEntitiesByDeletedAndSold(deleted,sold).stream().map(m -> this.getModelMapper().map(m,resultDto)).collect(Collectors.toList());
    }

    @Override
    public <T> List<T> findAllByDeletedAndSoldAndUsername(Class<T> resultDto, Boolean deleted, Boolean sold, String username) {
        return articleEntityRepository.findArticleEntitiesByDeletedAndSoldAndUserPersonUsername(deleted, sold, username).stream().map(m -> this.getModelMapper().map(m, resultDto)).collect(Collectors.toList());
    }
}
