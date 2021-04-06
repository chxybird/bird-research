package com.bird.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author lipu
 * @Date 2021/04/06 14:30
 * @Description 学生实体类
 */

@Data
@Accessors(chain = true)
public class Student {
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Integer mathGrade;
    private Integer englishGrade;
    private Integer chineseGrade;
}
