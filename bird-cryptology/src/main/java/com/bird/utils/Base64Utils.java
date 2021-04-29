package com.bird.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author lipu
 * @Date 2021/4/29 8:45
 * @Description
 */
public class Base64Utils {

    /**
     * @Author lipu
     * @Date 2021/4/29 8:47
     * @Description 编码
     */
    public static String encode(String content) {
        //使用UTF-8的编码将字符转换为字节再进行BASE64编码
        return Base64.getEncoder().encodeToString(content.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @Author lipu
     * @Date 2021/4/29 8:47
     * @Description 编码
     */
    public static String encode(byte[] bytes) {
        //使用UTF-8的编码将字符转换为字节再进行BASE64编码
        return Base64.getEncoder().encodeToString(bytes);
    }


    /**
     * @Author lipu
     * @Date 2021/4/29 8:47
     * @Description 解码
     */
    public static String decode(String baseCode) {
        byte[] bytes = Base64.getDecoder().decode(baseCode);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * @Author lipu
     * @Date 2021/4/29 8:47
     * @Description 解码 转字节数组
     */
    public static byte[] decodeToBytes(String baseCode) {
        return Base64.getDecoder().decode(baseCode);

    }


}
