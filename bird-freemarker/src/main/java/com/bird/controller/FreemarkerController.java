package com.bird.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author lipu
 * @Date 2021/4/11 12:18
 * @Description
 */
@RestController
@RequestMapping("/freemarker")
@Slf4j
public class FreemarkerController {

    /**
     * @Author lipu
     * @Date 2021/4/11 12:19
     * @Description freemarker的数据类型调研
     */
    @GetMapping("/dataType")
    public ModelAndView dataType(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("data-type");
        log.info("布尔类型测试");
        //布尔类型
        Boolean flag=true;
        modelAndView.addObject("flag",flag);
        return modelAndView;
    }
}
