package com.dee.blog_rest;


import com.dee.blog_rest.asecurity2.JwtAuthenticationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.Jsr310Converters;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = { BlogRestApplication.class, Jsr310Converters.class })
public class BlogRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogRestApplication.class, args);
    }

//    @PostConstruct
//    void init() {
//        TimeZone.setDefault(TimeZone.getTimeZone("WAT"));
//    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
