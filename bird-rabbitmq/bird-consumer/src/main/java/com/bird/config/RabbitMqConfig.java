package com.bird.config;


import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

/**
 * @Author lipu
 * @Date 2021/5/6 14:58
 * @Description
 */
public class RabbitMqConfig {


    /**
     * @Author lipu
     * @Date 2021/1/4 13:31
     * @Description 配置RabbitMQ的JSON序列化方式
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


}
