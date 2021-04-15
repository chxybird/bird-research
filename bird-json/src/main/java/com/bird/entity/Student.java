package com.bird.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author lipu
 * @Date 2021/4/15 9:16
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Date date;
}
