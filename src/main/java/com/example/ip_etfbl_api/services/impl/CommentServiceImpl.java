package com.example.ip_etfbl_api.services.impl;

import com.example.ip_etfbl_api.models.entities.UserCommentsArticleEntity;
import com.example.ip_etfbl_api.models.entities.UserEntity;
import com.example.ip_etfbl_api.models.responses.Comment;
import com.example.ip_etfbl_api.repositories.ArticleEntityRepository;
import com.example.ip_etfbl_api.repositories.UserCommentsArticleEntityRepository;
import com.example.ip_etfbl_api.repositories.UserEntityRepository;
import com.example.ip_etfbl_api.services.CommentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final UserCommentsArticleEntityRepository commentRepository;
    private final UserEntityRepository userEntityRepository;
    private final ModelMapper modelMapper;
    public CommentServiceImpl(UserCommentsArticleEntityRepository commentRepository, UserEntityRepository userEntityRepository, ArticleEntityRepository articleEntityRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<Comment> postAComment(Integer articleId, String username, String content) {
        Optional<UserEntity> user = userEntityRepository.findUserEntityByPersonUsernameAndPersonDeleted(username, false);
        if(user.isPresent() && content.length()<=500)
        {
            UserCommentsArticleEntity comment = new UserCommentsArticleEntity();
            comment.setUser(user.get());
            comment.setUserId(user.get().getId());
            comment.setArticleId(articleId);
            comment.setContent(content);
            Timestamp currentTime = Timestamp.from(Instant.now());
            comment.setDateTime(currentTime);
            UserCommentsArticleEntity result = commentRepository.save(comment);
            return Optional.of(modelMapper.map(result, Comment.class));
        }
        return Optional.empty();
    }
}
