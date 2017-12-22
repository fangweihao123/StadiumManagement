package com.example.MyBoot.configuration;


import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class SessionConfiguration {
    public final static String SESSION_PHONENUMBER = "phone";
    public final static String SESSION_USERNAME = "username";
}
