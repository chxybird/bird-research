package com.bird.config.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lipu
 * @Date 2021/5/7 9:46
 * @Description fanout调研配置
 */
@Configuration
public class FanoutConfig {
    public static final String DEFAULT_KEY="";
    public static final String EXCHANGE_FANOUT="BIRD-FANOUT";
    private static final String QUEUE_FANOUT_ONE="BIRD-FANOUT-QUEUE-ONE";
    private static final String QUEUE_FANOUT_TWO="BIRD-FANOUT-QUEUE-TWO";

    //交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(EXCHANGE_FANOUT,true,false);
    }

    //队列 队列一与队列二
    @Bean
    public Queue fanoutQueueOne(){
        return new Queue(QUEUE_FANOUT_ONE,true);
    }
    @Bean
    public Queue fanoutQueueTwo(){
        return new Queue(QUEUE_FANOUT_TWO,true);
    }

    //绑定关系
    @Bean
    public Binding fanoutBindingOne(){
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBindingTwo(){
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }

}
