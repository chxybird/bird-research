package com.bird.controller;

import com.bird.anotation.Permit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2021/6/11 14:38
 * @Description
 */
@RestController
@RequestMapping("/bird")
public class BirdController {


    /**
     * @Author lipu
     * @Date 2021/6/11 14:39
     * @Description 拦截器测试
     */
    @PostMapping("/interceptor")
    public String interceptor(){
        return "success";
    }

    /**
     * @Author lipu
     * @Date 2021/6/11 14:42
     * @Description 跳过拦截器测试
     */
    @PostMapping("/permit")
    @Permit
    public String permit(){
        return "success";
    }


}
