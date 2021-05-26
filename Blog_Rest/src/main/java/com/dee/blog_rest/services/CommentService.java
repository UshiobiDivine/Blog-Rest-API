package com.dee.blog_rest.services;

import com.dee.blog_rest.entities.Comment;
import com.dee.blog_rest.requests_and_responses.CommentRequest;
import com.dee.blog_rest.requests_and_responses.PostRequest;
import com.dee.blog_rest.requests_and_responses.PostResponse;
import com.dee.blog_rest.security.AuthenticationUserDetailService;

public interface CommentService {
    Comment addComment(CommentRequest commentRequest, Long userId, Long postId, AuthenticationUserDetailService currentUser);
}
