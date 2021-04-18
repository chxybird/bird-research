package com.bird.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author lipu
 * @Date 2021/4/16 15:16
 * @Description SpringSecurity工具类
 */
public class SecurityUtils {

    /**
     * @Author lipu
     * @Date 2021/4/16 15:29
     * @Description 获取当前用户信息
     */
    public static Object getUserInfo(){
        return SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }
}
