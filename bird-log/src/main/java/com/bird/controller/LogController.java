package com.bird.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2021/6/15 17:44
 * @Description
 */
@RestController
@Slf4j
public class LogController {

    @GetMapping("/log")
    public String log(){
        log.debug("小鸟程序员");
        log.info("小鸟程序员");
        log.warn("小鸟程序员");
        log.error("小鸟程序员");
        return "success";
    }
}
