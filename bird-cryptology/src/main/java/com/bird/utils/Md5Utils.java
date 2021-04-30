package com.bird.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author lipu
 * @Date 2021/4/30 14:21
 * @Description 单向散列函数MD5
 * 目前MD5的强碰撞性已经被破解 所以一般用于摘要的生成或者消息认证码(MAC)的生成
 */
@Slf4j
public class Md5Utils {
    /**
     * @Author lipu
     * @Date 2021/4/30 14:26
     * @Description 生成摘要
     */
    public static String initMac(String content) {
        if (content==null){
            log.info("消息为空,无法校验");
        }
        return DigestUtils.md5Hex(content);
    }

    /**
     * @Author lipu
     * @Date 2021/4/30 14:54
     * @Description 校验摘要
     */
    public static boolean verifyMac(String mac, String content) {
        if (mac == null || content == null) {
            System.out.println("消息认证码或者消息为空,无法校验");
            return false;
        }
        return DigestUtils.md5Hex(content).equals(mac);
    }
}
