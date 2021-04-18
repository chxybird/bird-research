package com.bird.controller;

import com.bird.entity.LoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/4/16 11:31
 * @Description
 */
@RestController
@RequestMapping("/user")
public class UserController {

//    @Resource
//    private UserDao userDao;

    @GetMapping("/findByEmail")
    public LoginUser findByEmail(@RequestParam String email){
//        LoginUser user = userDao.findByEmail(email);
//        return user;
        return null;
    }
}
