package com.example.ip_etfbl_api.controllers;

import com.example.ip_etfbl_api.models.requests.CommentRequest;
import com.example.ip_etfbl_api.models.responses.Comment;
import com.example.ip_etfbl_api.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add/{articleId}")
    public ResponseEntity<Comment> postComment(@PathVariable Integer articleId, @RequestBody String content, Authentication authentication)
    {
        String username = authentication.getName();
        Optional<Comment> result = commentService.postAComment(articleId, username, content);
        return result.map(comment -> ResponseEntity.status(200).body(comment)).orElseGet(() -> ResponseEntity.status(409).body(null));
    }

}
