package com.bird.security.handler;

import com.bird.entity.LoginUser;
import com.bird.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author lipu
 * @Date 2021/4/19 17:31
 * @Description
 */
@Slf4j
public class BirdLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * @Author lipu
     * @Date 2021/4/19 17:32
     * @Description 登出成功处理器
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取用户信息
        String token = request.getHeader(JwtUtils.AUTHORIZATION);
        LoginUser user = JwtUtils.getUser(token);
        //记录日志信息
        log.info(user.getUsername()+"退出系统");
    }
}
