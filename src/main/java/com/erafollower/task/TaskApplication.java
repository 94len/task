package com.erafollower.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@MapperScan("com.erafollower.task.mapper")
@EnableTransactionManagement
public class TaskApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
