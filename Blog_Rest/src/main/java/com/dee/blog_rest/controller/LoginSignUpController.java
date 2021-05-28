package com.dee.blog_rest.controller;

import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.requests_and_responses.ApiResponse;
import com.dee.blog_rest.requests_and_responses.SignUpRequest;
import com.dee.blog_rest.services.serviceImplementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/")
public class LoginSignUpController {

    Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserServiceImplementation userServiceImplementation;


//    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody() SignUpRequest signUpRequest){

        User user = userServiceImplementation.saveUser(signUpRequest);

            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{userId}")
                    .buildAndExpand(user.getId()).toUri();

            return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "User registered successfully"));
//        return ResponseEntity.ok().build();
    }


    @GetMapping("/userlogout")
    public ResponseEntity<ApiResponse> logoutPage(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
            new SecurityContextLogoutHandler().setClearAuthentication(true);
        }
//        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
        return ResponseEntity.ok().body(new ApiResponse(Boolean.TRUE, "User logged out successfully"));
    }

//    @PostMapping(value="/login")
//    public ResponseEntity<ApiResponse> login(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
////        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
//        return ResponseEntity.ok().body(new ApiResponse(Boolean.TRUE, "User registered successfully"));
//    }




//    {
//        "usernameOrEmail": "Dave@gmail.com",
//            "password":1234567
//    }

}
