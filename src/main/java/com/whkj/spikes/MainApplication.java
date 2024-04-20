package com.whkj.spikes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ServletComponentScan
@SpringBootApplication
@RestController
public class MainApplication {
    @GetMapping("/")
    public String hello() {
        return"Hello Spring Boot!";
    }
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}