package com.dee.blog_rest.services.serviceImplementation;

import com.dee.blog_rest.asecurity2.UserPrincipal;
import com.dee.blog_rest.entities.*;
import com.dee.blog_rest.repositories.CommentLikeRepository;
import com.dee.blog_rest.repositories.CommentRepository;
import com.dee.blog_rest.requests_and_responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentLikeService {

    @Autowired
    UserServiceImplementation userServiceImplementation;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    CommentRepository commentRepository;

    private CommentLikeRepository commentLikeRepository;

    @Autowired
    public CommentLikeService(CommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }

    public ApiResponse likeComment(Long commentId, UserPrincipal currentUser){
        if (commentId!=null){

            Optional<Comment> byId = commentRepository.findById(commentId);
            User user = userServiceImplementation.findById(currentUser.getId());

            CommentLike commentLikebyUser = commentLikeRepository.findByUser_Id(currentUser.getId());
            if (commentLikebyUser==null){
                CommentLike commentLike = new CommentLike();
                commentLike.setComment(byId.get());
                commentLike.setUser(user);
            }

//            if (postLike1==null){
//                PostLike postLike = new PostLike();
//                postLike.setPost(post);
//                postLike.setUser(byId);
//                commentService.save(postLike);
//                return new ApiResponse(Boolean.TRUE, "Post Liked");
//            }else if (postLike1!=null){
//                Post post1 = postLike1.getPost();
//                if (post1==post) {
//                    commentService.delete(postLike1);
//                    return new ApiResponse(Boolean.TRUE, "Post Unliked");
//                }
//            }


        }
        return new ApiResponse(Boolean.FALSE, "Could not like post");
    }

}
