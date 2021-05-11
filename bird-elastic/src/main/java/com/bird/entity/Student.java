package com.bird.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author lipu
 * @Date 2021/5/8 17:02
 * @Description
 */
@Data
@Document(indexName = "student")
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String id;
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
    @Field(type = FieldType.Date,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;
}
