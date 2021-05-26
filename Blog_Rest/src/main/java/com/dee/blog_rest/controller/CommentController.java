package com.dee.blog_rest.controller;


import com.dee.blog_rest.entities.Comment;
import com.dee.blog_rest.requests_and_responses.CommentRequest;
import com.dee.blog_rest.security.AuthenticationUserDetailService;
import com.dee.blog_rest.services.serviceImplementation.CommentServiceImpl;
import com.dee.blog_rest.services.serviceImplementation.PostServiceImple;
import com.dee.blog_rest.services.serviceImplementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class CommentController {

    @Autowired
    private AuthenticationUserDetailService currentUser;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private PostServiceImple postServiceImple;

    private CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping("users/{user_id}/posts/{post_id}/comments")
    public ResponseEntity<Comment> comment(@RequestBody CommentRequest commentRequest,
                                           @PathVariable(name = "user_id") Long userId,
                                           @PathVariable(name = "post_id") Long postId){
        Comment comment = commentService.addComment(commentRequest, userId, postId, currentUser);
        return ResponseEntity.ok(comment);
    }

}
