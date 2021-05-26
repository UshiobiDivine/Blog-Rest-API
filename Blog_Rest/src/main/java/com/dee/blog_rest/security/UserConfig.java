package com.dee.blog_rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig  {
//
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository repository){
//        return args -> {
//            User dee = new User("Dee", "Obi", "Deeobi13@gmail.com", "123456");
//            User john = new User("John", "Obi", "Johnobi13@gmail.com", "qwerty");
//            User abi = new User("Abi", "Obi", "Abiobi13@gmail.com", "asdfgh");
//
//            repository.saveAll(List.of(dee, john, abi));
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
