package com.hyperzsb.spacemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpaceManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpaceManagerApplication.class, args);
    }
}
