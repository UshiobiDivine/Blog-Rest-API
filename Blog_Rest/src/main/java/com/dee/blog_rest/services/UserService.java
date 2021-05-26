package com.dee.blog_rest.services;

import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.requests_and_responses.SignUpRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User saveUser(SignUpRequest signUpRequest);
    List<User> findAll();
    void updateUser(Long id,String email,
                    String firstName,
                    String lastName,
                    String password);
    boolean userExist(String email);
    User findByMail(String email);
}
