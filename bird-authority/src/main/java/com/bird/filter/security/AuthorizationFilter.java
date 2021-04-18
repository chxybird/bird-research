package com.bird.filter.security;

import com.bird.entity.LoginUser;
import com.bird.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @Author lipu
 * @Date 2021/4/16 15:44
 * @Description 认证过滤器链
 */
@Slf4j
public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * @author lipu
     * @date 2020/7/21 13:55
     * @Description JWT令牌校验
     */
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取令牌信息
        String token = request.getHeader(JwtUtils.AUTHORIZATION);
        //如果没有登录
        if (token == null) {
            log.info("请先登录");
            chain.doFilter(request, response);
        } else {
            try {
                //如果登录 校验token
                JwtUtils.parseJwt(token);
                //从token中获取用户信息
                LoginUser user = JwtUtils.getUser(token);
                Collection<? extends GrantedAuthority> authorityList = user.getAuthorities();
                //构造Security对象
                UsernamePasswordAuthenticationToken anthRequest = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorityList);
                //将对象交给Security来处理
                SecurityContextHolder.getContext().setAuthentication(anthRequest);
                chain.doFilter(request, response);
            } catch (IOException e) {
                log.info("令牌非法");
                chain.doFilter(request, response);
            }
        }
    }

}