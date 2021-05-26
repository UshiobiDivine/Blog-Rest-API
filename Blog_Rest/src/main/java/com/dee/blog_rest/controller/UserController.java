package com.dee.blog_rest.controller;

import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.services.serviceImplementation.UserServiceImplementation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    Logger logger = Logger.getLogger(UserController.class.getName());

    private UserServiceImplementation userServiceImplementation;

    @Autowired
    public UserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }



//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public String save(@RequestBody() User user){
//
//            userServiceImplementation.saveUser(user);
//            return "User saved successfully";
//
//    }

    @GetMapping
    public List<User> allUsers(){
        return userServiceImplementation.findAll();
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String password,
                           @RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName){

        logger.info(userId.toString());
        logger.info(email);
        logger.info(password);
        logger.info(firstName);
        logger.info(lastName);
        userServiceImplementation.updateUser(userId, email, password, firstName, lastName);
    }
}
