package com.bird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author lipu
 * @Date 2021/4/6 14:55
 * @Description
 */
@SpringBootApplication
@MapperScan("com.bird.dao")
public class MybatisPlusApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApp.class);
    }
}
