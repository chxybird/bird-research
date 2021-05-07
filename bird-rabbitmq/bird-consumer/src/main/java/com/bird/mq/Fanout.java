package com.bird.mq;

import com.bird.entity.Coupon;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author lipu
 * @Date 2021/5/7 10:03
 * @Description
 */
@Component
public class Fanout {
    public static final String EXCHANGE_TYPE = "fanout";
    public static final String EXCHANGE_FANOUT = "BIRD-FANOUT";
    private static final String QUEUE_FANOUT_ONE = "BIRD-FANOUT-QUEUE-ONE";
    private static final String QUEUE_FANOUT_TWO = "BIRD-FANOUT-QUEUE-TWO";

    /**
     * @Author lipu
     * @Date 2021/5/7 10:05
     * @Description 监听fanout队列一
     */
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = QUEUE_FANOUT_ONE, durable = "true"),
            exchange = @Exchange(value = EXCHANGE_FANOUT, type = EXCHANGE_TYPE))
    })
    public void queueOne(String msg) {
        System.out.println("队列一" + msg);
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 10:05
     * @Description 监听fanout队列二
     */
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = QUEUE_FANOUT_TWO, durable = "true"),
            exchange = @Exchange(value = EXCHANGE_FANOUT, type = EXCHANGE_TYPE))
    })
    public void queueTwo(String msg) {
        System.out.println("队列二" + msg);
    }
}
