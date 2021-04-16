package com.bird;

import com.bird.entity.Student;
import com.bird.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * @Author lipu
 * @Date 2021/4/15 9:04
 * @Description
 */
@SpringBootTest(classes = JsonApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JsonTest {
    @Resource
    private JsonUtils jsonUtils;




    /**
     * @Author lipu
     * @Date 2021/4/15 15:29
     * @Description 对象转json文件
     */
    @Test
    public void objectAsFile(){
        Set<Student> studentList=new HashSet<>();
        studentList.add(new Student(16036024L, "小鸟", 23, "男", new Date()));
        studentList.add(new Student(16036025L, "小", 24, "男", new Date()));
        JsonUtils.entityToFile("F:\\","bird",studentList);
    }

    @Test
    public void testDate(){
        //反序列化测试
        String json="{\n" +
                "    \"date\":\"2021-4-16 00:00:00\"\n" +
                "}";
        Student student = JsonUtils.jsonToObject(json, Student.class);
        System.out.println(student);
        //序列化测试
        String toJson = JsonUtils.entityToJson(student);
        System.out.println(toJson);

    }
}
