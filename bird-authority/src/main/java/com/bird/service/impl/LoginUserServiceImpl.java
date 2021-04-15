package com.bird.service.impl;

import com.bird.dao.*;

import com.bird.service.LoginUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @Author lipu
 * @Date 2021/4/15 17:30
 * @Description
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Resource
    private UserDao userDao;

    /**
     * @Author lipu
     * @Date 2021/4/15 17:33
     * @Description SpringSecurity认证处理
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //数据库获取用户信息完成用户认证
        return userDao.findByEmail(email);
    }
}
