package com.bird.config.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2021/5/7 11:42
 * @Description 死信队列调研
 */
@Configuration
public class DeadConfig {

    public static final String EXCHANGE_DEAD = "BIRD-DEAD";
    private static final String QUEUE_DEAD = "BIRD-DEAD-QUEUE";
    public static final String KEY_QUEUE_DEAD = "KEY-TTL-DEAD";

    //交换机
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(EXCHANGE_DEAD, true, false);
    }

    //死信队列
    @Bean
    public Queue queueDead() {
        return new Queue(QUEUE_DEAD,true);
    }

    //绑定关系
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(queueDead()).to(deadExchange()).with(KEY_QUEUE_DEAD);
    }

}
