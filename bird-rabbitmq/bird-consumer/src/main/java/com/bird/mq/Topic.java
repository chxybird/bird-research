package com.bird.mq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author lipu
 * @Date 2021/5/7 10:44
 * @Description
 */
@Component
public class Topic {
    public static final String EXCHANGE_TYPE="topic";
    public static final String EXCHANGE_TOPIC = "BIRD-TOPIC";
    private static final String QUEUE_TOPIC_ONE = "BIRD-TOPIC-QUEUE-ONE";
    private static final String QUEUE_TOPIC_TWO = "BIRD-TOPIC-QUEUE-TWO";
    //符号“#”匹配路由键的一个或多个词，符号“*”匹配路由键的一个词
    public static final String KEY_QUEUE_ONE="#.bird.*";
    public static final String KEY_QUEUE_TWO="*.bird.#";

    /**
     * @Author lipu
     * @Date 2021/5/7 10:05
     * @Description 监听topic队列一
     */
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = QUEUE_TOPIC_ONE, durable = "true"),
            exchange = @Exchange(value = EXCHANGE_TOPIC,type = EXCHANGE_TYPE),
            key = KEY_QUEUE_ONE)
    })
    public void queueOne(String msg) {
        System.out.println("队列一" + msg);
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 10:05
     * @Description 监听topic队列二
     */
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = QUEUE_TOPIC_TWO, durable = "true"),
            exchange = @Exchange(value = EXCHANGE_TOPIC,type = EXCHANGE_TYPE),
            key = KEY_QUEUE_TWO)
    })
    public void queueTwo(String msg) {
        System.out.println("队列二" + msg);
    }

}
