package com.bird.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Author lipu
 * @Date 2021/4/20 16:26
 * @Description 自定义退出登录过滤器
 */
@Slf4j
public class CusLogoutFilter extends LogoutFilter {


    public CusLogoutFilter(LogoutSuccessHandler logoutSuccessHandler, LogoutHandler... handlers) {
        super(logoutSuccessHandler, handlers);
        log.info("走了自定义的退出过滤器");
    }


}
