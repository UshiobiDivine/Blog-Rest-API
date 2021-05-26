package com.dee.blog_rest.services.serviceImplementation;

import com.dee.blog_rest.repositories.PostLikeRepository;
import com.dee.blog_rest.requests_and_responses.ApiResponse;
import com.dee.blog_rest.requests_and_responses.LikeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {

    private PostLikeRepository postLikeRepository;

    @Autowired
    private UserServiceImplementation userServiceImplementation;
    @Autowired
    private PostServiceImple postServiceImple;

    @Autowired
    public PostLikeService(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }
//    public ApiResponse like(LikeRequest likeRequest, Long userId, Long postId){
//
//    }
}
