package com.dee.blog_rest.services;

import com.dee.blog_rest.asecurity2.UserPrincipal;
import com.dee.blog_rest.entities.Post;
import com.dee.blog_rest.requests_and_responses.ApiResponse;
import com.dee.blog_rest.requests_and_responses.PagedResponse;
import com.dee.blog_rest.requests_and_responses.PostRequest;
import com.dee.blog_rest.requests_and_responses.PostResponse;

public interface PostService222 {

	PagedResponse<Post> getAllPosts(int page, int size);

	PagedResponse<Post> getPostsByCreatedBy(String username, int page, int size);
	Post updatePost(Long id, PostRequest newPostRequest, UserPrincipal currentUser);

	ApiResponse deletePost(Long id, UserPrincipal currentUser);

	PostResponse addPost(PostRequest postRequest, UserPrincipal currentUser);

	Post getPost(Long id);

}
