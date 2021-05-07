package com.bird.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bird.dao.CouponDao;
import com.bird.entity.Coupon;
import com.bird.utils.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.tools.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
    public void receive(String msg) {
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

    }
}
