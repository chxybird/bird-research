package com.bird.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Author lipu
 * @Date 2021/5/26 10:42
 * @Description
 */
@Data
@Document(collection = "student")
public class Student implements Serializable {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Integer mathGrade;
    private Integer englishGrade;
    private Integer chineseGrade;
}
