package com.dee.blog_rest.repositories;

import com.dee.blog_rest.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByUser_IdAndFollowing_Id(Long User_id, Long following_id);
    Follow findByFollowing_Id(Long follower_id);
}
