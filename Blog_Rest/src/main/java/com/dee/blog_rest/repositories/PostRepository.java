package com.dee.blog_rest.repositories;

import com.dee.blog_rest.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    Page<Post> findByCreatedBy(Long userId, Pageable pageable);

    List<Post> findPostByUserId(Long id);
    void deleteById(Long postId);
}