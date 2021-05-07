package com.bird.config.mq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2021/5/7 11:10
 * @Description 延时队列调研
 * 通过给队列设置ttl来完成消息的过期时间或者直接给消息设置ttl,两者都有取最短
 */
@Configuration
public class TtlConfig {
    public static final String EXCHANGE_DIRECT_TTL = "BIRD-DIRECT-TTL";
    private static final String QUEUE_TTL = "BIRD-TTL-QUEUE";
    public static final String KEY_QUEUE_TTL = "KEY-TTL";
    public static final String TTL_CONFIG = "x-message-ttl";
    public static final String DEAD_EXCHANGE_CONFIG = "x-dead-letter-exchange";
    public static final String DEAD_KEY_CONFIG = "x-dead-letter-routing-key";


    //交换机
    @Bean
    public DirectExchange ttlExchange() {
        return new DirectExchange(EXCHANGE_DIRECT_TTL, true, false);
    }

    //队列 ttl队列
    @Bean
    public Queue queueTtl() {
        //设置队列的ttl 单位毫秒
        Map<String, Object> config = new HashMap<>();
        config.put(TTL_CONFIG, 30000);
        //设置过期消息转发到死信队列
        config.put(DEAD_EXCHANGE_CONFIG,DeadConfig.EXCHANGE_DEAD);
        config.put(DEAD_KEY_CONFIG,DeadConfig.KEY_QUEUE_DEAD);
        //设置队列的最大长度

        return new Queue(QUEUE_TTL, true, false, false, config);
    }

    //绑定关系
    @Bean
    public Binding ttlBinding() {
        return BindingBuilder.bind(queueTtl()).to(ttlExchange()).with(KEY_QUEUE_TTL);
    }


    /**
     * @Author lipu
     * @Date 2021/5/7 11:30
     * @Description 消息过期时间配置
     */
    @Bean
    public MessagePostProcessor messagePostProcessor(){
        return new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置消息的相关特性 这里设置ttl
                message.getMessageProperties().setContentEncoding("UTF-8");
                //20秒过期
                message.getMessageProperties().setExpiration("20000");
                return message;
            }
        };
    }


}
