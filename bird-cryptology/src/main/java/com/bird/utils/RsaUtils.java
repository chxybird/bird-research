package com.bird.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2021/4/29 14:47
 * @Description 非对称加密 密钥长度 1024 2048 4096
 */
@Slf4j
public class RsaUtils {
    private static final String ALGORITHM = "RSA";
    private static final Integer DEFAULT_LENGTH = 1024;
    public static final String PUBLIC_KEY = "public";
    public static final String PRIVATE_KEY = "private";



    /**
     * @Author lipu
     * @Date 2021/4/29 14:54
     * @Description 生成公钥和私钥对
     */
    public static Map<String,Key> initKey() {
        try {
            //KeyPairGenerator 用于生成公钥与私钥对
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            //设置密钥的长度 这里走自定义默认1024 走默认的安全随机数
            keyPairGenerator.initialize(DEFAULT_LENGTH);
            //生成密钥对
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            //获取公钥的BASE64编码
            PublicKey publicKey = keyPair.getPublic();
//            String publicEncode = Base64Utils.encode(publicKey.getEncoded());
            //获取私钥的BASE64编码
            PrivateKey privateKey = keyPair.getPrivate();
//            String privateEncode = Base64Utils.encode(privateKey.getEncoded());
            //组装公钥私钥密码对
            HashMap<String,Key> keyMap=new HashMap<>();
            keyMap.put(PUBLIC_KEY,publicKey);
            keyMap.put(PRIVATE_KEY,privateKey);
            return keyMap;
        } catch (Exception e) {
            log.info("生成密钥对失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/29 15:36
     * @Description 加密
     */
    public String encrypt(String content,Map<String,Key> keyMap) {
        if (content==null){
            log.info("加密内容为空不加密");
            return null;
        }
        if (keyMap==null||keyMap.get(PUBLIC_KEY)==null){
            log.info("公钥为空,加密失败");
            return null;
        }
        try {
            Cipher cipher=Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keyMap.get(PRIVATE_KEY));
            byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64Utils.encode(bytes);
        } catch (Exception e) {
            log.info("加密失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/29 15:36
     * @Description 解密
     */
    public String decrypt(String content,Map<String,Key> keyMap) {
        if (content==null){
            log.info("解密内容为空不解密");
            return null;
        }
        if (keyMap==null||keyMap.get(PRIVATE_KEY)==null){
            log.info("私钥为空,解密失败");
            return null;
        }
        try {
            //BASE64解密
            byte[] bytes = Base64Utils.decodeToBytes(content);
            Cipher cipher=Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keyMap.get(PRIVATE_KEY));
            return new String(cipher.doFinal(bytes),StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.info("解密失败");
            return null;
        }
    }

    public static void main(String[] args) {

    }
}
