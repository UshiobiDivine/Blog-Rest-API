package com.dee.blog_rest.services.serviceImplementation;

import com.dee.blog_rest.asecurity2.UserPrincipal;
import com.dee.blog_rest.entities.Post;
import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.entities.role.RoleName;
import com.dee.blog_rest.exceptions.BadRequestException;
import com.dee.blog_rest.exceptions.UnauthorizedException;
import com.dee.blog_rest.repositories.PostRepository222;
import com.dee.blog_rest.repositories.UserRepository;
import com.dee.blog_rest.requests_and_responses.ApiResponse;
import com.dee.blog_rest.requests_and_responses.PagedResponse;
import com.dee.blog_rest.requests_and_responses.PostRequest;
import com.dee.blog_rest.requests_and_responses.PostResponse;
import com.dee.blog_rest.services.PostService222;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class PostServiceImpl implements PostService222 {
	@Autowired
	private PostRepository222 postRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public PagedResponse<Post> getAllPosts(int page, int size) {
		validatePageNumberAndSize(page, size);

		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

		Page<Post> posts = postRepository.findAll(pageable);

		List<Post> content = posts.getNumberOfElements() == 0 ? Collections.emptyList() : posts.getContent();

		return new PagedResponse<>(content, posts.getNumber(), posts.getSize(), posts.getTotalElements(),
				posts.getTotalPages(), posts.isLast());
	}

	@Override
	public PagedResponse<Post> getPostsByCreatedBy(String email, int page, int size) {
		validatePageNumberAndSize(page, size);
		User user = userRepository.findByEmail(email);
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
		Page<Post> posts = postRepository.findByCreatedBy(user.getId(), pageable);

		List<Post> content = posts.getNumberOfElements() == 0 ? Collections.emptyList() : posts.getContent();

		return new PagedResponse<>(content, posts.getNumber(), posts.getSize(), posts.getTotalElements(),
				posts.getTotalPages(), posts.isLast());
	}


	@Override
	public Post updatePost(Long id, PostRequest newPostRequest, UserPrincipal currentUser) {
		Post post = postRepository.findById(id).orElseThrow(() -> new IllegalStateException("Resource not found"));

		if (post.getUser().getId().equals(currentUser.getId())
				|| currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			post.setCaption(newPostRequest.getTitle());
			post.setBody(newPostRequest.getBody());
			post.setUpdatedAt(Instant.now());
			return postRepository.save(post);
		}
		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to edit this post");

		throw new UnauthorizedException(apiResponse);
	}

	@Override
	public ApiResponse deletePost(Long id, UserPrincipal currentUser) {
		Post post = postRepository.findById(id).orElseThrow(() -> new IllegalStateException("Resource not found"));
		if (post.getUser().getId().equals(currentUser.getId())
				|| currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
			postRepository.deleteById(id);
			return new ApiResponse(Boolean.TRUE, "You successfully deleted post");
		}

		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete this post");

		throw new UnauthorizedException(apiResponse);
	}

	@Override
	public PostResponse addPost(PostRequest postRequest, UserPrincipal currentUser) {
		User user = userRepository.findById(currentUser.getId())
				.orElseThrow(() -> new IllegalStateException("Resource not found"));

		Post post = new Post();
		post.setBody(postRequest.getBody());
		post.setCaption(postRequest.getTitle());
		post.setCreatedAt(Instant.now());
		post.setUpdatedAt(Instant.now());
		post.setCreatedBy(user.getFirstName()+" "+user.getLastName());

		post.setUser(user);


		Post newPost = postRepository.save(post);

		PostResponse postResponse = new PostResponse();

		postResponse.setTitle(newPost.getCaption());
		postResponse.setBody(newPost.getBody());

		return postResponse;
	}

	@Override
	public Post getPost(Long id) {
		return postRepository.findById(id).orElseThrow(() -> new IllegalStateException("Resource not found"));
	}

	private void validatePageNumberAndSize(int page, int size) {
		if (page < 0) {
			throw new BadRequestException("Page number cannot be less than zero.");
		}

		if (size < 0) {
			throw new BadRequestException("Size number cannot be less than zero.");
		}

		if (size > 30) {
			throw new BadRequestException("Page size must not be greater than " + 30);
		}
	}
}
