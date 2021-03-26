package com.bird.controller;

import com.bird.entity.Student;
import com.bird.service.StudentService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/3/26 11:53
 * @Description 学生控制层
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;



    /**
     * @Author lipu
     * @Date 2021/3/26 21:28
     * @Description 查询所有学生
     */
    @GetMapping("/findAll")
    public List<Student> findAll(){
        return studentService.findAll();
    }
}
