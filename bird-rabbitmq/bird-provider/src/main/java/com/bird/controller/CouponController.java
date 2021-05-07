package com.bird.controller;

import com.bird.entity.Coupon;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/5/6 14:10
 * @Description
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * @Author lipu
     * @Date 2021/5/6 14:11
     * @Description 抢券 模拟1000并发
     */
    @PostMapping("/scramble")
    public String scramble(){
        Coupon coupon=new Coupon();
        coupon.setId(1L);
        rabbitTemplate.convertAndSend("bird-coupon","scramble",coupon);
        return "success";
    }
}
