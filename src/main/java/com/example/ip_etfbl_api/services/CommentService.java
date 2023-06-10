package com.example.ip_etfbl_api.services;

import com.example.ip_etfbl_api.models.responses.Comment;

import java.util.Optional;

public interface CommentService {

    Optional<Comment> postAComment(Integer articleId, String username, String content);
}
