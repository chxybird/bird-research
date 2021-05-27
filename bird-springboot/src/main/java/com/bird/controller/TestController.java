package com.bird.controller;

import com.bird.anotation.Idempotence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2021/5/27 11:03
 * @Description
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * @Author lipu
     * @Date 2021/5/27 11:04
     * @Description 接口幂等性校验测试
     */
    @PostMapping("/idempotence")
    @Idempotence
    public String idempotence(){
        return "success";
    }
}
