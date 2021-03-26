package com.bird.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/3/22 14:00
 * @Description 学生实体类
 */
@Data
@TableName("t_student")
public class Student {
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelProperty("序号")
    private Long id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private Integer age;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("数学成绩")
    @ColumnWidth(20)
    private Integer mathGrade;
    @ExcelProperty("英语成绩")
    @ColumnWidth(20)
    private Integer englishGrade;
    @ExcelProperty("语文成绩")
    @ColumnWidth(20)
    private Integer chineseGrade;
    @ExcelIgnore
    private Long dId;
}
