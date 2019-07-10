package com.example.jpademo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication


//@EntityScan用来扫描和发现指定包及其子包中的Entity定义
@EntityScan(basePackages = {"com.example.jpademo.demo.entities"})
//在Application 中启用审计
@EnableJpaAuditing
//需要添加@EnableJpaRepositories注解扫描和发现指定包及其子包中的Repository定义
@EnableJpaRepositories("com.example.jpademo.demo.Repository")

public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}
