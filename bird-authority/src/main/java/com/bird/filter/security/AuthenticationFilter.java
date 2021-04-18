package com.bird.filter.security;

import com.bird.entity.LoginUser;

import com.bird.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author lipu
 * @Date 2021/4/16 15:43
 * @Description 认证过滤器链
 */
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final ObjectMapper objectMapper=new ObjectMapper();

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * @author lipu
     * @date 2020/7/21 15:14
     * @Description 异步请求比对数据库进行用户信息认证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            //忽略从表单获取数据 从异步请求体中获取用户信息
            LoginUser user = objectMapper.readValue(request.getInputStream(), LoginUser.class);
            String username = user.getUsername();
            String password = user.getPassword();
            log.info("用户名"+username+"密码"+password);
            //将获取的用户信息分装并执行authenticationManager.authenticate(),Security会自动调用UserDetailsService。
            UsernamePasswordAuthenticationToken authRequest=new UsernamePasswordAuthenticationToken(username,password);
            return authenticationManager.authenticate(authRequest);
        } catch (IOException e) {
            log.info("认证失败");
        }
        log.info("认证失败");
        throw new RuntimeException();
    }

    /**
     * @author lipu
     * @date 2020/7/21 13:32
     * @Description 认证通过后返回token
     */
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //从Security容器中获取用户信息
        LoginUser user = (LoginUser) authResult.getPrincipal();
        //设置信息
        Map<String,Object> map=new HashMap<>();
        map.put("id",user.getId());
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        map.put("phone",user.getPhone());
        map.put("isOpen",user.getIsOpen());
        map.put("roleList",user.getRoleList());
        map.put("email",user.getEmail());
        //记录用户信息到JWT中
        String token = JwtUtils.initJwt(map);
        log.info("token生成成功");
        //将token存入到请求头中
        response.addHeader(JwtUtils.AUTHORIZATION,token);

    }
}
