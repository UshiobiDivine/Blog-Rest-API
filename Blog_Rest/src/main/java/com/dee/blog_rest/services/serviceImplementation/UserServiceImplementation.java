package com.dee.blog_rest.services.serviceImplementation;

import com.dee.blog_rest.entities.User;
import com.dee.blog_rest.exceptions.BadRequestException;
import com.dee.blog_rest.repositories.UserRepository;
import com.dee.blog_rest.requests_and_responses.ApiResponse;
import com.dee.blog_rest.requests_and_responses.SignUpRequest;
import com.dee.blog_rest.requests_and_responses.UpdateUserRequest;
import com.dee.blog_rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;
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
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }
        throw new BadRequestException(
                "User not found", new Throwable("Invalid user id"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new IllegalStateException("Student with id " + userId + " does not exist"));


        User user1 = userRepository.findByEmail(updateUserRequest.getEmail());
        if (Optional.ofNullable(user1).isPresent()){
            throw new IllegalStateException("email taken");
        }
        user.setEmail(updateUserRequest.getEmail());
        user.setUpdatedAt(Instant.now());
        user.setPassword(updateUserRequest.getPassword());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());

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

    @Override
    public boolean deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
        return true;
    }
}
