package com.dee.blog_rest.controller;

import com.dee.blog_rest.entities.Follow;
import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.repositories.FollowRepository;
import com.dee.blog_rest.repositories.UserRepository;
import com.dee.blog_rest.requests_and_responses.ApiResponse;
import com.dee.blog_rest.security.CurrentUser;
import com.dee.blog_rest.security.UserPrincipal;
import com.dee.blog_rest.services.FollowService;
import com.dee.blog_rest.services.serviceImplementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/")
public class FollowingController {

    private UserServiceImplementation userServiceImplementation;
    private UserRepository userRepository;

    @Autowired
    private FollowService followService;


    @Autowired
    public FollowingController(UserServiceImplementation userServiceImplementation, UserRepository userRepository) {
        this.userServiceImplementation = userServiceImplementation;
        this.userRepository = userRepository;
    }




    @PostMapping("follow/{userIdToFollow}")
    public ResponseEntity<ApiResponse> follow(@PathVariable(name = "userIdToFollow") Long userId,
                                              @CurrentUser UserPrincipal currentUser) {
        ApiResponse followed = followService.follow(userId, currentUser);
        return ResponseEntity.ok(followed);
    }


    @PostMapping("unfollow/{userIdToUnfollow}")
    public ResponseEntity<ApiResponse> unfollow(@PathVariable(name = "userIdToUnfollow") Long userId,
                                                @CurrentUser UserPrincipal currentUser) {
        ApiResponse unfollowed = followService.unfollow(userId, currentUser);
        return ResponseEntity.ok(unfollowed);
    }
}
