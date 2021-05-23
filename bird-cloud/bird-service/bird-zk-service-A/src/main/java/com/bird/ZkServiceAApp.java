package com.bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author lipu
 * @Date 2021/5/23 22:15
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZkServiceAApp {
    public static void main(String[] args) {
        SpringApplication.run(ZkServiceAApp.class);
    }
}
