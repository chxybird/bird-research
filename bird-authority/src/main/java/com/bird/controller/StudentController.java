package com.bird.controller;

import com.bird.entity.LoginUser;
import com.bird.utils.SecurityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        //获取当前用户信息
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.toString());
        return "success";
    }


}
