package com.bird.config;

import com.bird.interceptor.BirdInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/6/11 14:49
 * @Description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private BirdInterceptor birdInterceptor;

    /**
     * 配置拦截器以及拦截路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(birdInterceptor)
                .addPathPatterns("/bird/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout");
    }
}
