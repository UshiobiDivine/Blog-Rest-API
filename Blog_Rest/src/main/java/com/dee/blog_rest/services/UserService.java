package com.dee.blog_rest.services;

import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.requests_and_responses.SignUpRequest;
import com.dee.blog_rest.requests_and_responses.UpdateUserRequest;

import java.util.List;


public interface UserService {
    User saveUser(SignUpRequest signUpRequest);
    List<User> findAll();
    void updateUser(Long userId, UpdateUserRequest updateUserRequest);
    boolean userExist(String email);
    User findByMail(String email);
    boolean deleteUser(Long userId);

}
