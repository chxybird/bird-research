package com.bird.config.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lipu
 * @Date 2021/5/7 10:44
 * @Description
 */
@Configuration
public class TopicConfig {
    public static final String EXCHANGE_TOPIC = "BIRD-TOPIC";
    private static final String QUEUE_TOPIC_ONE = "BIRD-TOPIC-QUEUE-ONE";
    private static final String QUEUE_TOPIC_TWO = "BIRD-TOPIC-QUEUE-TWO";
    //符号“#”匹配路由键的一个或多个词，符号“*”匹配路由键的一个词
    public static final String KEY_QUEUE_ONE="#.bird.*";
    public static final String KEY_QUEUE_TWO="*.bird.#";


    //交换机
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_TOPIC, true, false);
    }

    //队列 队列一与队列二
    @Bean
    public Queue topicQueueOne() {
        return new Queue(QUEUE_TOPIC_ONE, true);
    }

    @Bean
    public Queue topicQueueTwo() {
        return new Queue(QUEUE_TOPIC_TWO, true);
    }

    //绑定关系
    @Bean
    public Binding topicBindingOne() {
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(KEY_QUEUE_ONE);
    }
    @Bean
    public Binding topicBindingTwo() {
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(KEY_QUEUE_TWO);
    }

}
