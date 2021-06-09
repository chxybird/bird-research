package com.bird.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bird.dao.CouponDao;
import com.bird.entity.Coupon;
import com.bird.utils.JsonUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author lipu
 * @Date 2021/5/6 14:56
 * @Description
 */
@Component
@Slf4j
public class CouponComponent {

    @Resource
    private CouponDao couponDao;

    /**
     * @Author lipu
     * @Date 2021/5/6 15:05
     * @Description 抢券
     */
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = "coupon-scramble"),
            exchange = @Exchange(value = "bird-coupon", type = "topic"),
            key = {"scramble"})
    })
    public void receive(String msg, Channel channel, Message message) throws IOException {
        try{
            Coupon coupon;
            coupon = JsonUtils.jsonToObject(msg, Coupon.class);
            if (coupon==null){
                return;
            }
            coupon = couponDao.selectById(coupon.getId());
            if (coupon==null){
                return;
            }
            //获取库存 更新库存
            if (coupon.getStock() > 0) {
                coupon.setStock(coupon.getStock()-1);
                couponDao.update(coupon,new QueryWrapper<Coupon>().eq("id",coupon.getId()));
            }
            //手动确认 消息index 是否批量
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            //后续业务处理

        }
    }

    /**
     * @Author lipu
     * @Date 2021/6/7 18:09
     * @Description 可靠消息投递
     */
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(value = "BIRD-DIRECT-QUEUE-ONE"),
            exchange = @Exchange(value = "BIRD-DIRECT", type = "direct"),
            key = {"BIRD-DIRECT-QUEUE-ONE"})
    })
    public void ack(String msg, Channel channel, Message message) throws IOException {
        try{
            System.out.println("消息为:"+msg);
            //手动确认 消息index 是否批量
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            log.info("消息处理失败");
        }
    }


}
