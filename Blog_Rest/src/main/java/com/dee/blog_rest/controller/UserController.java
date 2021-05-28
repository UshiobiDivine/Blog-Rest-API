package com.dee.blog_rest.controller;

import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.requests_and_responses.ApiResponse;
import com.dee.blog_rest.requests_and_responses.UpdateUserRequest;
import com.dee.blog_rest.requests_and_responses.UserDetailsResponse;
import com.dee.blog_rest.services.serviceImplementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


    @GetMapping
    public List<UserDetailsResponse> allUsers(){
        List<User> all = userServiceImplementation.findAll();

        List<UserDetailsResponse> users = new ArrayList<>();
        all.forEach(user -> {
            UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
            userDetailsResponse.setId(user.getId());
            userDetailsResponse.setEmail(user.getEmail());
            userDetailsResponse.setDateCreated(user.getCreatedAt().toString());
            userDetailsResponse.setFirstName(user.getFirstName());
            userDetailsResponse.setLastName(user.getLastName());
            userDetailsResponse.setTotalNumberOfConnections(user.getConnections().size());
            userDetailsResponse.setTotalNumberOfPosts(user.getPosts().size());
            users.add(userDetailsResponse);
        });
        return users;
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("userId") Long userId,
                                                  @Valid @RequestBody UpdateUserRequest updateUserRequest){
        if (userServiceImplementation.findById(userId)!=null){

            logger.info(updateUserRequest.getLastName()+" "+
                    updateUserRequest.getFirstName()+" "+
                    updateUserRequest.getLastName()+" "+
                    updateUserRequest.getPassword()+" "+
                    updateUserRequest.getEmail());

            userServiceImplementation.updateUser(userId,updateUserRequest);
            return ResponseEntity.ok(new ApiResponse(Boolean.TRUE, "User details updated successfully"));
        }
        return (ResponseEntity<ApiResponse>) ResponseEntity.notFound();

    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId){

        User user = userServiceImplementation.findById(userId);
        Optional<User> user1 = Optional.ofNullable(user);
        if (user1.isPresent()){
            return ResponseEntity.ok(user1.get());
        }
        return (ResponseEntity<User>) ResponseEntity.notFound();
    }


    @Scheduled(initialDelay = 1000 * 10, fixedDelay=Long.MAX_VALUE)
    @DeleteMapping(path = "{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Long userId){

        User user = userServiceImplementation.findById(userId);
        Optional<User> user1 = Optional.ofNullable(user);
        if (user1.isPresent()){
            boolean b = userServiceImplementation.deleteUser(userId);
            if(b)
                return ResponseEntity.ok(new ApiResponse(Boolean.TRUE, "User account deleted!!!"));
        }
        return ResponseEntity.ok(new ApiResponse(Boolean.FALSE, "Can't delete account"));
    }

}
