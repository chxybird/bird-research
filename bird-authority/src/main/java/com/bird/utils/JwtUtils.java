package com.bird.utils;

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
    public static final String HEADER_FLAG = "Authorization";

    public static final String HEADER = "header";

    public static final String BODY = "body";

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
    public static String initJwt(Map<String, Object> map) {
        //设置过期时间
        TIME.add(Calendar.HOUR, 2);
        JwtBuilder builder = Jwts.builder()
                //唯一ID
                .setId(UUID.randomUUID().toString())
                //颁发人
                .setIssuer(ISSUER)
                //颁发时间
                .setIssuedAt(new Date())
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
    public static Map<String, Object> parseJwt(String jwt) {
        //令牌解析
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();
        Map<String, Object> info = new HashMap<>();
        info.put("header", header);
        info.put("body", body);
        return info;
    }

    /**
     * @Author lipu
     * @Date 2021/1/21 17:06
     * @Description 获取用户认证信息id
     */
    public static Long getStaffInfo(String jwt) {
        //令牌解析
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
        Claims body = claimsJws.getBody();
        return body.get("ID", Long.class);
    }

}
