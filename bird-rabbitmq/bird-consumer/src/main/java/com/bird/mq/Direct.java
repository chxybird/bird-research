package com.bird.mq;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author lipu
 * @Date 2021/5/7 10:38
 * @Description
 */
@Component
public class Direct {
    public static final String EXCHANGE_DIRECT = "BIRD-DIRECT";
    private static final String QUEUE_DIRECT_ONE = "BIRD-DIRECT-QUEUE-ONE";
    private static final String QUEUE_DIRECT_TWO = "BIRD-DIRECT-QUEUE-TWO";
    public static final String KEY_QUEUE_ONE="direct-one";
    public static final String KEY_QUEUE_TWO="direct-two";

    /**
     * @Author lipu
     * @Date 2021/5/7 10:05
     * @Description 监听direct队列一
     */
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = QUEUE_DIRECT_ONE, durable = "true"),
            exchange = @Exchange(value = EXCHANGE_DIRECT),
            key = KEY_QUEUE_ONE)
    })
    public void queueOne(String msg) {
        System.out.println("队列一" + msg);
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 10:05
     * @Description 监听direct队列二
     */
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = QUEUE_DIRECT_TWO, durable = "true"),
            exchange = @Exchange(value = EXCHANGE_DIRECT),
            key = KEY_QUEUE_TWO)
    })
    public void queueTwo(String msg) {
        System.out.println("队列二" + msg);
    }
}
