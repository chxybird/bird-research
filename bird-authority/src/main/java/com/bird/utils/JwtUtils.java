package com.bird.utils;

import com.bird.entity.LoginUser;
import com.bird.entity.Role;
import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.*;

import java.util.*;

/**
 * @Author lipu
 * @Date 2020/12/18 20:29
 * @Description JWT工具类
 */
public class JwtUtils {
    /**
     * 认证头部的KEY标识
     */
    public static final String AUTHORIZATION = "Authorization";

    public static final Calendar TIME = Calendar.getInstance();
    /**
     * 颁发者信息
     */
    public static final String ISSUER = "小鸟程序员";
    /**
     * BASE64编码前的密钥信息
     */
    public static final String SECRET_KEY = "BIRD";
    /**
     * 最终密钥信息
     */
    public static final String KEY = Base64.getMimeEncoder().encodeToString(SECRET_KEY.getBytes());


    /**
     * @Author lipu
     * @Date 2020/12/18 20:40
     * @Description 创建jwt令牌
     */
    public static String initJwt(Map<String,Object> map) {
        //设置过期时间
        TIME.add(Calendar.HOUR, 2);
        JwtBuilder builder = Jwts.builder()
                //唯一ID
                .setId(UUID.randomUUID().toString())
                //颁发人
                .setIssuer(ISSUER)
                //颁发时间
                .setIssuedAt(new Date())
                //设置其他载荷信息
                .setClaims(map)
                //加密算法和密钥(盐)
                .signWith(SignatureAlgorithm.HS256, KEY)
                .setExpiration(TIME.getTime());
        return builder.compact();
    }

    /**
     * @Author lipu
     * @Date 2020/12/18 22:55
     * @Description 令牌解析
     */
    public static void parseJwt(String jwt) {
        //令牌解析
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(KEY).parseClaimsJws(jwt);
    }


    /**
     * @Author lipu
     * @Date 2021/4/16 16:19
     * @Description 获取用户信息
     */
    public static LoginUser getUser(String jwt){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
        Claims body = claimsJws.getBody();
        LoginUser user=new LoginUser();
        user.setId(Long.valueOf(String.valueOf(body.get("id"))));
        user.setUsername(body.get("username").toString());
        user.setPassword(body.get("password").toString());
        user.setEmail(body.get("email").toString());
        user.setPhone(body.get("phone").toString());
        user.setIsOpen(Integer.parseInt(body.get("isOpen").toString()));
        Object list = body.get("roleList");
        String json = JsonUtils.entityToJson(list);
        List<Role> roleList = JsonUtils.jsonToList(json, Role.class);
        user.setRoleList(roleList);
        return user;
    }


}
