package com.bird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author lipu
 * @Date 2021/4/14 9:41
 * @Description RBAC权限调研
 */

@SpringBootApplication
@MapperScan("com.bird.dao")
public class AuthorityApp {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityApp.class);
    }
}
