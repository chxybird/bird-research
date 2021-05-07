package com.bird.config.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lipu
 * @Date 2021/5/7 10:26
 * @Description
 */
@Configuration
public class DirectConfig {

    public static final String EXCHANGE_DIRECT = "BIRD-DIRECT";
    private static final String QUEUE_DIRECT_ONE = "BIRD-DIRECT-QUEUE-ONE";
    private static final String QUEUE_DIRECT_TWO = "BIRD-DIRECT-QUEUE-TWO";
    public static final String KEY_QUEUE_ONE="direct-one";
    public static final String KEY_QUEUE_TWO="direct-two";

    //交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_DIRECT, true, false);
    }

    //队列 队列一与队列二
    @Bean
    public Queue directQueueOne() {
        return new Queue(QUEUE_DIRECT_ONE, true);
    }

    @Bean
    public Queue directQueueTwo() {
        return new Queue(QUEUE_DIRECT_TWO, true);
    }

    //绑定关系
    @Bean
    public Binding directBindingOne() {
        return BindingBuilder.bind(directQueueOne()).to(directExchange()).with(KEY_QUEUE_ONE);
    }
    @Bean
    public Binding directBindingTwo() {
        return BindingBuilder.bind(directQueueTwo()).to(directExchange()).with(KEY_QUEUE_TWO);
    }

}
