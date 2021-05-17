package com.bird.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentLoopMerge;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * @Author lipu
 * @Date 2021/3/22 14:00
 * @Description 学生实体类
 */
@Data
@TableName("t_student")
@ContentStyle(
        horizontalAlignment= HorizontalAlignment.CENTER,
        verticalAlignment = VerticalAlignment.CENTER,
        borderTop = BorderStyle.THIN,
        borderBottom = BorderStyle.THIN,
        borderLeft = BorderStyle.THIN,
        borderRight = BorderStyle.THIN
)
@HeadFontStyle(fontName = "黑体")
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
    @ExcelProperty("总人数")
    @ColumnWidth(20)
    @ContentLoopMerge(eachRow = 20)
    @TableField(exist = false)
    private Integer count;
}
