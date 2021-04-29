package com.bird.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @Author lipu
 * @Date 2021/4/28 13:59
 * @Description 对称加密AES(分组密码) 工具类
 * 密钥长度128、192、256(16字节、24字节、32字节)
 */
@Slf4j
public class AesUtils {

    private static final String AES_KEY="BD59Uia5qTYSDcs7";
    private static final String ALGORITHM="AES";

    /**
     * @Author lipu
     * @Date 2021/4/29 9:57
     * @Description 加密
     */
    public static String encrypt(String content){
        if (content==null){
            log.info("内容为空不做加密处理");
            return null;
        }
        try {
            //获取加密解密对象 加密算法/加密模式/填充方式
            Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
            //初始化密钥
            SecretKeySpec secretKeySpec=new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8),ALGORITHM);
            //加密对象初始化 设置为加密
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
            //加密
            byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64Utils.encode(bytes);
        } catch (Exception e) {
            log.info("加密失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/29 10:50
     * @Description 解密
     */
    public static String decrypt(String content){
        if (content==null){
            log.info("内容为空不做解密处理");
            return null;
        }
        try {
            //BASE64解密
            byte[] bytes = Base64Utils.decodeToBytes(content);
            //获取加密解密对象 加密算法/加密模式/填充方式
            Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
            //初始化密钥
            SecretKeySpec secretKeySpec=new SecretKeySpec(AES_KEY.getBytes(StandardCharsets.UTF_8),ALGORITHM);
            //加密对象初始化 设置为解密
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            //解密
            return new String(cipher.doFinal(bytes),StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.info("解密失败");
            return null;
        }
    }

}
