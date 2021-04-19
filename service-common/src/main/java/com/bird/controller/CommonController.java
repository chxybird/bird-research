package com.bird.controller;

import com.bird.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author lipu
 * @Date 2021/4/18 20:53
 * @Description
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    /**
     * @Author lipu
     * @Date 2021/4/19 8:43
     * @Description 数据初始化
     */
    public Student initStudent(){
        Student student=new Student();
        student.setName("小鸟");
        student.setAge(22);
        student.setId(1L);
        student.setChineseGrade(135);
        student.setMathGrade(130);
        student.setEnglishGrade(120);
        student.setSex("男");
        return student;
    }

    /**
     * @Author lipu
     * @Date 2021/4/19 8:38
     * @Description GET 参数拼接接口
     */
    @GetMapping("/getParam")
    public Student getParam(Student student){
        log.info("id:"+student.getId());
        return initStudent();
    }

    /**
     * @Author lipu
     * @Date 2021/4/19 8:44
     * @Description GET
     */
    @GetMapping("/getPath/{id}")
    public Student getPath(@PathVariable("id") Long id,String name){
        log.info("id:"+id);
        log.info("姓名为:"+name);
        return initStudent();
    }

    @GetMapping("/getBody")
    public Student getBody(@RequestBody Student student){
        log.info(student.toString());
        return initStudent();
    }

    @PostMapping("/postBody")
    public Student postBody(@RequestBody Student student){
        log.info(student.toString());
        return initStudent();
    }

    @PostMapping(value = "/postForm")
    public Student postForm(Student student){
        log.info(student.toString());
        return initStudent();
    }



    @DeleteMapping("/deleteBody")
    public Student deleteBody(@RequestBody Student student){
        log.info(student.toString());
        return initStudent();
    }






}
