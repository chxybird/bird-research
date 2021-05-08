package com.bird.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author lipu
 * @Date 2021/5/8 17:02
 * @Description
 */
@Data
@Document(indexName = "student_index")
public class Student {
    @Id
    @Field(type = FieldType.Long, store = true)
    private Long id;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_smart")
    private String name;
    @Field(type = FieldType.Long,store = true)
    private Integer age;
    @Field(type = FieldType.Keyword,store = true)
    private String sex;
    @Field(type = FieldType.Long,store = true)
    private Integer mathGrade;
    @Field(type = FieldType.Long,store = true)
    private Integer englishGrade;
    @Field(type = FieldType.Long,store = true)
    private Integer chineseGrade;
}
