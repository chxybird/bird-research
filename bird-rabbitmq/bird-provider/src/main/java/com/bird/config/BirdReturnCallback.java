package com.bird.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author lipu
 * @Date 2021/6/7 17:04
 * @Description 消息从交换机投递到队列的确认机制 失败时候触发
 */
@Component
@Slf4j
public class BirdReturnCallback implements RabbitTemplate.ReturnCallback {

    /**
     *
     * @param message 投递的消息体
     * @param i 响应code
     * @param s 响应内容
     * @param s1 交换机
     * @param s2 队列
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("投递消息到队列失败");
        //后续逻辑处理
    }
}
