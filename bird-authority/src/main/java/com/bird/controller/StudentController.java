package com.bird.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lipu
 * @Date 2021/4/14 10:15
 * @Description
 */
@RestController
@RequestMapping("/student")
public class StudentController {


    @GetMapping("/findAll")
    public String findAll(){
        return "success";
    }


}
