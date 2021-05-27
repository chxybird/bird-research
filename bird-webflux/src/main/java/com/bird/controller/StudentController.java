package com.bird.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2021/5/25 10:46
 * @Description
 */
@RestController
public class StudentController {

    @GetMapping("/getInfo")
    public String getInfo(){
        return "success";
    }
}
