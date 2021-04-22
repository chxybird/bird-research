package com.bird.security.handler;

import com.bird.common.CommonResult;
import com.bird.constant.ResponseCode;
import com.bird.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author lipu
 * @Date 2021/4/20 13:53
 * @Description 权限不足处理器
 */
@Component
@Slf4j
public class CusAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * @Author lipu
     * @Date 2021/4/20 14:11
     * @Description 自定义权限不足处理逻辑
     */
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        //前后端分离响应状态码全部设置为200
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String jsonResult = JsonUtils.entityToJson(new CommonResult<>(null, ResponseCode.ACCESS_DENIED, "权限不足"));
        response.getWriter().write(jsonResult);
    }
}
