package com.dee.blog_rest.services.serviceImplementation;

import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.exceptions.BadRequestException;
import com.dee.blog_rest.repositories.UserRepository;
import com.dee.blog_rest.requests_and_responses.ApiResponse;
import com.dee.blog_rest.requests_and_responses.SignUpRequest;
import com.dee.blog_rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//
//    @Override
//    public boolean saveUser(User user) {
//
//        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
//        user.setDateOpened(timestamp);
//        User save = userRepository.save(user);
//        if (save!=null){
//            return true;
//        }
//        return false;
//
//
//
//        if (userRepository.findByEmail(user.getEmail())!=null) {
//            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
//            throw new BadRequestException(apiResponse);
//        }
//
//        List<Role> roles = new ArrayList<>();
//        roles.add(
//                roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
//        user.setRoles(roles);
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//       User save = userRepository.save(user);
//        if (save!=null){
//            return true;
//        }
//        return false;
//    }


    @Override
    public User saveUser(SignUpRequest signUpRequest) {
        User user = new User();
        if (userRepository.findByEmail(signUpRequest.getEmail())!=null) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
            throw new BadRequestException(apiResponse.getMessage());
        }


        user.setEmail(signUpRequest.getEmail());
        user.setLastName(signUpRequest.getLastName());
        user.setFirstName(signUpRequest.getFirstName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        User save = userRepository.save(user);
        return save;
    }

    public  User findById(Long id){
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void updateUser(Long userId,
                           String email,
                           String firstName,
                           String lastName,
                           String password) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new IllegalStateException("Student with id " + userId + " does not exist"));
        if (firstName!=null&&firstName.length()>0&& !Objects.equals(user.getFirstName(), firstName)){
            user.setFirstName(firstName);
        }
        if (lastName!=null&&lastName.length()>0&& !Objects.equals(user.getLastName(), lastName)){
            user.setLastName(lastName);
        }
        if (password!=null&&password.length()>0&& !Objects.equals(user.getPassword(), password)){
            user.setPassword(firstName);
        }

        User user1 = userRepository.findByEmail(email);
        if (Optional.ofNullable(user1).isPresent()){
            throw new IllegalStateException("email taken");
        }
        user.setEmail(email);
        System.out.println("USER UPDATED SUCCESSFULLY");
    }

    @Override
    public boolean userExist(String email) {
        User byEmail = userRepository.findByEmail(email);
        if (Optional.ofNullable(byEmail).isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public User findByMail(String email) {
        return userRepository.findByEmail(email);
    }
}
