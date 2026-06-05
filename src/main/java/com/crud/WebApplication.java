package com.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application for CRUD REST API
 * Run with: mvn spring-boot:run
 * Or: mvn exec:java -Dexec.mainClass="com.crud.WebApplication"
 */
@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
