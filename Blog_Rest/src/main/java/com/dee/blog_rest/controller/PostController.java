package com.dee.blog_rest.controller;

import com.dee.blog_rest.entities.Post;
import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.requests_and_responses.ApiResponse;
import com.dee.blog_rest.requests_and_responses.PostRequest;
import com.dee.blog_rest.requests_and_responses.PostResponse;
import com.dee.blog_rest.security.AuthenticationUserDetailService;
import com.dee.blog_rest.services.serviceImplementation.PostServiceImple;
import com.dee.blog_rest.services.serviceImplementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class PostController {

    private PostServiceImple postServiceImple;

    @Autowired
    private AuthenticationUserDetailService currentUser;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    public PostController(PostServiceImple postServiceImple) {
        this.postServiceImple = postServiceImple;
    }

    @PostMapping("users/{user_id}/posts")
    public ResponseEntity<PostResponse> addPost(@Valid @RequestBody PostRequest postRequest,
                                                @PathVariable(name = "user_id") Long userId) {
        User byId = userServiceImplementation.findById(userId);

        PostResponse postResponse = postServiceImple.addPost(postRequest, byId.getEmail(), currentUser);

        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("users/{user_id}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable(name = "user_id") Long userId) {
        List<Post> byUserId = postServiceImple.findByUserId(userId);
        return ResponseEntity.ok(byUserId);
    }


    @DeleteMapping("users/{user_id}/posts/{post_id}")
    public ResponseEntity<ApiResponse> deleteApost(@PathVariable(name = "user_id") Long userId,
                                                  @PathVariable(name = "post_id") Long postId) {
//            List<Post> byUserId = postServiceImple.findByUserId(userId);
            postServiceImple.deletePost(postId);
            return ResponseEntity.ok(new ApiResponse(Boolean.FALSE.TRUE, "post deleted"));
    }

    @PutMapping("users/{user_id}/posts/{post_id}")
    public ResponseEntity<ApiResponse> updatePost(@Valid @RequestBody PostRequest postRequest,
                                                   @PathVariable(name = "user_id") Long userId,
                                                   @PathVariable(name = "post_id") Long postId) {
        postServiceImple.updatePost(postId, postRequest);
        return ResponseEntity.ok(new ApiResponse(Boolean.TRUE, "Post updated successfully"));
    }


}
