package com.bird.security.handler;

import com.bird.common.CommonResult;
import com.bird.constant.ResponseCode;
import com.bird.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author lipu
 * @Date 2021/4/20 13:55
 * @Description 认证异常处理器
 */
@Component
@Slf4j
public class CusAuthenticationHandler implements AuthenticationEntryPoint {

    /**
     * @Author lipu
     * @Date 2021/4/20 14:00
     * @Description 自定义认证异常逻辑
     */
    @Override
    public void commence(HttpServletRequest  request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        //前后端分离响应状态码全部设置为200
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String jsonResult = JsonUtils.entityToJson(new CommonResult<>(null, ResponseCode.AUTHENTICATE_FAIL, "认证异常"));
        response.getWriter().write(jsonResult);
    }
}
