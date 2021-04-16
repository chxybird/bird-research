package com.bird.config;

import com.bird.service.LoginUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;

import javax.annotation.Resource;
import java.beans.Customizer;

/**
 * @Author lipu
 * @Date 2021/4/15 21:49
 * @Description SpringSecurity配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LoginUserService loginUserService;



    /**
     * @Author lipu
     * @Date 2021/4/16 10:07
     * @Description 加密策略配置 BCrypt
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        //加密策略
        return new BCryptPasswordEncoder();
    }


    /**
     * @Author lipu
     * @Date 2021/4/16 10:22
     * @Description 配置用户的来源(数据库)以及加密方式
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUserService)
                .passwordEncoder(bCryptPasswordEncoder());
    }


    /**
     * @Author lipu
     * @Date 2021/4/16 11:14
     * @Description SpringSecurity规则配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //配置路径拦截策略
                .antMatchers("/login").permitAll()//配置不拦截的资源
                .antMatchers("/dormitory/**").hasAnyRole("ADMIN")
                //剩余的接口随意访问
                .anyRequest().authenticated()
                //使用默认登录页面
                .and().formLogin()
                //配置关闭csrf
                .and().csrf().disable();
    }

}
