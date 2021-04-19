package com.bird.entity;

import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/04/06 14:30
 * @Description 学生实体类
 */

@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Integer mathGrade;
    private Integer englishGrade;
    private Integer chineseGrade;
}
