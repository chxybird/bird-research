package com.bird.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author lipu
 * @Date 2021/5/14 14:59
 * @Description
 */
@Data
public class User {
    private String username;
    private String phone;
    private String birthday;
    private Double salary;
    private String entryDay;
    private String province;
    private String address;
    private Integer age;
    private String city;
    private String imgUrl;
}
