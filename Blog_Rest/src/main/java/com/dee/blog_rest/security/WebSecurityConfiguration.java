//package com.dee.blog_rest.configs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private final AuthenticationUserDetailService authenticationUserDetailService;
//
//    public WebSecurityConfiguration(AuthenticationUserDetailService authenticationUserDetailService) {
//        this.authenticationUserDetailService = authenticationUserDetailService;
//    }
//
////    @Override
////    public void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable().authorizeRequests()
////                .antMatchers("/").permitAll()
////                .antMatchers(HttpMethod.POST,"/users").permitAll()
////                .antMatchers(HttpMethod.POST, "/login").permitAll()
////                .antMatchers(HttpMethod.POST,"/newuser/*").permitAll()
////                .antMatchers(HttpMethod.GET,"/master/*").permitAll()
////                .antMatchers(HttpMethod.GET,"/exploreCourse").permitAll()
////                .anyRequest().authenticated();
////    }
//
////    @Override
////    protected void configure(HttpSecurity security) throws Exception
////    {
////        security.httpBasic().disable();
////    }
//
////    @Autowired
////    private DataSource dataSource;
//
//    /*@Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsService();
//    }*/
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//   /* @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }*/
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http       //other configure params.
//                .csrf().disable();
//
//        http.cors().and().csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.SIGN_UP_URL).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                // this disables session creation on Spring Security
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        /*http.authorizeRequests()
//                .antMatchers("/users").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .usernameParameter("email")
//                .defaultSuccessUrl("/users")
//                .permitAll()
//                .and()
//                .logout().logoutSuccessUrl("/").permitAll();*/
//    }
//
//
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(authenticationUserDetailService).passwordEncoder(passwordEncoder());
//    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(authenticationProvider());
////    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/users").authenticated()
////                .anyRequest().permitAll()
////                .and()
////                .formLogin()
////                .usernameParameter("email")
////                .defaultSuccessUrl("/users")
////                .permitAll()
////                .and()
////                .logout().logoutSuccessUrl("/").permitAll();
////    }
//}
