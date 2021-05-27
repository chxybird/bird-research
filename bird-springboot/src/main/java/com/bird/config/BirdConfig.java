package com.bird.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author lipu
 * @Date 2021/5/24 19:34
 * @Description
 */
@ConfigurationProperties(prefix = "bird")
@Component
@Data
public class BirdConfig {
    /**
     * 网络地址
     */
    private String ip;
    /**
     * 端口号
     */
    private Integer port;
    /**
     * 名称
     */
    private String name;
}
