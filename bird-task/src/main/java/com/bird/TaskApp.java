package com.bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author lipu
 * @Date 2021/4/2 11:01
 * @Description
 */
@SpringBootApplication
@EnableScheduling
public class TaskApp {
    public static void main(String[] args) {
        SpringApplication.run(TaskApp.class);
    }
}
