package com.dee.blog_rest.services.serviceImplementation;

import com.dee.blog_rest.entities.Comment;
import com.dee.blog_rest.entities.Post;
import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.repositories.CommentRepository;
import com.dee.blog_rest.requests_and_responses.CommentRequest;
import com.dee.blog_rest.requests_and_responses.PostResponse;
import com.dee.blog_rest.security.AuthenticationUserDetailService;
import com.dee.blog_rest.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    UserServiceImplementation userServiceImplementation;

    @Autowired
    private PostServiceImple postServiceImple;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment addComment(CommentRequest commentRequest, Long userId, Long postId, AuthenticationUserDetailService currentUser) {
        Post post = postServiceImple.findPost(postId);
        User user = userServiceImplementation.findById(userId);

        Comment comment = new Comment();
        comment.setBody(commentRequest.getBody());
        comment.setPost(post);
        comment.setCreatedAt(Instant.now());
        comment.setUpdatedAt(Instant.now());
        comment.setUser(user);
        Comment save = commentRepository.save(comment);
        return save;
    }
}
