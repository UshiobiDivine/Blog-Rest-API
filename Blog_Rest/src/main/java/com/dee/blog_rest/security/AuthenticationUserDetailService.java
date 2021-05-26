package com.dee.blog_rest.security;

import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.services.serviceImplementation.UserServiceImplementation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationUserDetailService implements UserDetailsService {

    private final UserServiceImplementation userService;

    @Override public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User apiUser = userService.findByMail(usernameOrEmail);
        if (apiUser == null) {
            throw new UsernameNotFoundException(usernameOrEmail);
        }
        return new org.springframework.security.core.userdetails.User(apiUser.getEmail(), apiUser.getPassword(), Collections.emptyList());
    }
}
