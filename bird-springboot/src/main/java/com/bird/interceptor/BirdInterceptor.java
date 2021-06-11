package com.bird.interceptor;

import com.bird.anotation.Permit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author lipu
 * @Date 2021/6/11 14:44
 * @Description 拦截器 拦截BirdController
 */
@Component
@Slf4j
public class BirdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果有自定义注解Permit不拦截
        HandlerMethod method = (HandlerMethod) handler;
        Permit permit = method.getMethodAnnotation(Permit.class);
        if (permit!=null){
            return true;
        }
        String token = request.getHeader("token");
        //TODO 根据token在redis中查询当前用户信息(校验token) 这里跳过
        if (token==null){
            String msg="请登录!!!";
            response.getOutputStream().write(msg.getBytes());
            return false;
        }else {
            return true;
        }
    }
}
