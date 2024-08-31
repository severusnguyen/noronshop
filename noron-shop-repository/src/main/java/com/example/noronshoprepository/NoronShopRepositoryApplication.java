package com.example.noronshoprepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class NoronShopRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoronShopRepositoryApplication.class, args);
    }

}
