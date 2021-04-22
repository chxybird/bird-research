package com.bird.controller;

import com.bird.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author lipu
 * @Date 2021/4/20 9:40
 * @Description
 */
@RestController
@Slf4j
public class LoginController {

    /**
     * @Author lipu
     * @Date 2021/4/20 9:41
     * @Description 登录成功后的逻辑
     */
    @GetMapping("/loginSuccess")
    public CommonResult<String> loginSuccess(HttpServletRequest request, HttpServletResponse response){
        log.info("登录成功");
        return new CommonResult<String>(null,200,"登录成功");
    }
}
