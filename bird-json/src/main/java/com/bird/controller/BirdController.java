package com.bird.controller;

import com.bird.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/4/15 15:11
 * @Description
 */
@RestController
public class BirdController {

    @GetMapping("/json")
    public List<Student> json(){
        Student student=new Student();
        student.setDate(new Date());
        student.setName("张三");
        List<Student> studentList=new ArrayList<>();
        studentList.add(student);
        return studentList;
    }

    @PostMapping("/date")
    public Student dateTest(@RequestBody Student student){
        return student;
    }
}
