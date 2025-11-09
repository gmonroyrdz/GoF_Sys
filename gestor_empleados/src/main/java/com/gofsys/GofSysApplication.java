package com.gofsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gofsys", "business", "dal", "pl"})
public class GofSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(GofSysApplication.class, args);
    }
}
