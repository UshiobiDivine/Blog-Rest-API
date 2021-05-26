package com.dee.blog_rest.security;

public class AuthenticationConfigConstants {
    public static final String SECRET = "mysecret";
    public static final long EXPIRATION_TIME = 600000; // 1 minute
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/signup";
}
