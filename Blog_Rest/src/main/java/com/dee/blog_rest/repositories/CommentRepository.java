package com.dee.blog_rest.repositories;

import com.dee.blog_rest.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CommentRepository extends JpaRepository<Comment, Long>{
}