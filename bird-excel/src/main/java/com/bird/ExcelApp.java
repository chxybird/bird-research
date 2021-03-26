package com.bird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author lipu
 * @Date 2021/3/24 13:26
 * @Description
 */
@SpringBootApplication
@MapperScan("com.bird.dao")
public class ExcelApp {
    public static void main(String[] args) {
        SpringApplication.run(ExcelApp.class);
    }
}
