package com.dee.blog_rest.services.serviceImplementation;

import com.dee.blog_rest.entities.Post;
import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.repositories.PostRepository;
import com.dee.blog_rest.requests_and_responses.PostRequest;
import com.dee.blog_rest.requests_and_responses.PostResponse;
import com.dee.blog_rest.security.AuthenticationUserDetailService;
import com.dee.blog_rest.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class PostServiceImple implements PostService {

    @Autowired
    AuthenticationUserDetailService currentUser;

    @Autowired
    UserServiceImplementation userServiceImplementation;

    private PostRepository postRepository;

    @Autowired
    public PostServiceImple(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post findPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public PostResponse addPost(PostRequest postRequest, String email, AuthenticationUserDetailService currentUser) {

        String userEmail = currentUser.loadUserByUsername(email).getUsername();
        User user = userServiceImplementation.findByMail(userEmail);

        Post post = new Post();
        post.setBody(postRequest.getBody());
        post.setCaption(postRequest.getTitle());
        post.setCreatedAt(Instant.now());
        post.setUpdatedAt(Instant.now());
        post.setUser(user);
        post.setCreatedBy(user.getFirstName()+" "+user.getLastName());

        postRepository.save(post);

        PostResponse postResponse = new PostResponse();
        postResponse.setTitle(post.getCaption());
        postResponse.setBody(post.getBody());

        return postResponse;
    }

    @Override
    public void deletePost(Long id) {
        if (id!=null){
        postRepository.findById(id).ifPresent((post)->
                postRepository.deleteById(id));
        }else {
            throw new IllegalStateException("post does not exist");
        }

    }

    @Transactional
    @Override
    public void updatePost(Long id, PostRequest postRequest) {
        if (id!=null){
            postRepository.findById(id).ifPresent((post -> {
                post.setCaption(postRequest.getTitle());
                post.setBody(postRequest.getBody());
            }));
        }
    }


}
