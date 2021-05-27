package com.bird.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author lipu
 * @Date 2021/5/24 17:59
 * @Description
 */
@PropertySource(
        value = "classpath:properties/bird.properties",
        ignoreResourceNotFound = true
)
@Data
@Component
public class BirdProperties {
    @Value("${ip}")
    private String ip;
    @Value("${port}")
    private Integer port;
    @Value("${name}")
    private String name="localhost";

}
