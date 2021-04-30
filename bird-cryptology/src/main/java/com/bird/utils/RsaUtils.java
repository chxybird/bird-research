package com.bird.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
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
            //获取私钥的BASE64编码
            PrivateKey privateKey = keyPair.getPrivate();
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
    public static String encrypt(String content,Map<String,Key> keyMap) {
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
            cipher.init(Cipher.ENCRYPT_MODE, keyMap.get(PUBLIC_KEY));
            byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64Utils.encode(bytes);
        } catch (Exception e) {
            log.info("加密失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/29 17:39
     * @Description 加密
     */
    public static String encrypt(String content,PublicKey publicKey) {
        if (content==null||publicKey==null){
            log.info("加密内容或者公钥为空不加密");
            return null;
        }
        try {
            Cipher cipher=Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64Utils.encode(bytes);
        } catch (Exception e) {
            log.info("加密失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/29 17:41
     * @Description 解密
     */
    public static String decrypt(String content,PrivateKey privateKey) {
        if (content==null||privateKey==null){
            log.info("解密内容为空或者私钥为空不解密");
            return null;
        }
        try {
            //BASE64解密
            byte[] bytes = Base64Utils.decodeToBytes(content);
            Cipher cipher=Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(bytes),StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.info("解密失败");
            return null;
        }
    }


    /**
     * @Author lipu
     * @Date 2021/4/29 15:36
     * @Description 解密
     */
    public static String decrypt(String content,Map<String,Key> keyMap) {
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

    /**
     * @Author lipu
     * @Date 2021/4/29 16:50
     * @Description 生成密钥对到磁盘
     */
    public static void initToFile(String path){
        if (path==null){
            log.info("没有指定公钥私钥写入路径");
            return;
        }
        try{
            Map<String, Key> keyMap = initKey();
            Key publicKey = keyMap.get(PUBLIC_KEY);
            Key privateKey = keyMap.get(PRIVATE_KEY);
            //写入公钥
            JsonUtils.entityToFile(path,PUBLIC_KEY,publicKey);
            //写入私钥
            JsonUtils.entityToFile(path,PRIVATE_KEY,privateKey);
        }catch (Exception e){
            log.info("密钥对写入磁盘失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/29 17:04
     * @Description 读取私钥
     */
    public static PrivateKey getPrivate(String path){
        try{
            return (PrivateKey) JsonUtils.fileToEntity(path, RsaUtils.PUBLIC_KEY, Object.class);
        }catch (Exception e){
            log.info("类型转换异常,请确保文件私钥格式");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/29 17:04
     * @Description 读取公钥
     */
    public static RSAPublicKey getPublic(String path){
        try{
            return JsonUtils.fileToEntity(path, RsaUtils.PUBLIC_KEY, RSAPublicKey.class);
        }catch (Exception e){
            log.info("类型转换异常,请确保文件公钥格式");
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        ClassPathResource classPathResource=new ClassPathResource("rsa");
        String path = classPathResource.getFile().getPath();
        RSAPublicKey aPublic = RsaUtils.getPublic(path);

//        PrivateKey privateKey = RsaUtils.getPrivate(path);
//        String encrypt = RsaUtils.encrypt("小鸟程序员", publicKey);
//        System.out.println("加密后的数据为"+encrypt);
//        String decrypt = RsaUtils.decrypt(encrypt, privateKey);
//        System.out.println("解密后的数据为"+decrypt);

    }
}
