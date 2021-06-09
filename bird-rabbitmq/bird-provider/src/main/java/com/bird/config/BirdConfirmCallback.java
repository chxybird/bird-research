package com.bird.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/6/7 15:46
 * @Description 消息确认机制 消息投递到交换机时的确认机制
 */
@Component
@Slf4j
public class BirdConfirmCallback implements RabbitTemplate.ConfirmCallback {
    /**
     * rabbitmq的可靠消息投递有两种实现方式,方式一采用事务的方式(性能损失巨大),第二种使用生产者确认机制。
     * 通过生产者确认机制，生产者可以在消息被服务器成功接收时得到反馈，并有机会处理未被成功接收的消息。
     */

    /**
     *
     * @param correlationData 消息唯一性ID
     * @param ack 消息确认
     * @param cause 投递失败的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息的ID为"+correlationData);
        //消息投递失败
        if (!ack){
            log.info("消息投递失败!!!");
            //执行后续业务逻辑
        }else {
            log.info("消息投递成功");
        }
    }
}
