package com.dee.blog_rest.services;

import com.dee.blog_rest.entities.Post;
import com.dee.blog_rest.requests_and_responses.PostRequest;
import com.dee.blog_rest.requests_and_responses.PostResponse;
import com.dee.blog_rest.security.AuthenticationUserDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    Post findPost(Long postId);
    List<Post> findByUserId(Long userId);
    PostResponse addPost(PostRequest postRequest, String email, AuthenticationUserDetailService currentUser);
    void deletePost(Long id);
    void updatePost(Long id, PostRequest postRequest);
}
