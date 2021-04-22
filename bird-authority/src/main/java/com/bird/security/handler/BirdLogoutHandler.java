package com.bird.security.handler;

import com.bird.entity.LoginUser;
import com.bird.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author lipu
 * @Date 2021/4/19 18:08
 * @Description 自定义退出处理器
 */
@Slf4j
public class BirdLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    }


}
