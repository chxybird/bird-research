import com.bird.ProviderApp;
import com.bird.config.mq.DirectConfig;
import com.bird.config.mq.FanoutConfig;
import com.bird.config.mq.TopicConfig;
import com.bird.entity.Coupon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/5/7 9:53
 * @Description
 */
@SpringBootTest(classes = ProviderApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RabbitMqTest {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * @Author lipu
     * @Date 2021/5/7 9:59
     * @Description Fanout模式调研
     */
    @Test
    public void fanout(){
        Coupon coupon=new Coupon();
        coupon.setId(10L);
        coupon.setStock(200);
        coupon.setName("工商银行100元通用券");
        rabbitTemplate.convertAndSend(FanoutConfig.EXCHANGE_FANOUT,FanoutConfig.DEFAULT_KEY,coupon);
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 10:32
     * @Description Direct模式调研
     */
    @Test
    public void direct(){
        Coupon coupon=new Coupon();
        coupon.setId(10L);
        coupon.setStock(200);
        coupon.setName("工商银行100元通用券");
        rabbitTemplate.convertAndSend(DirectConfig.EXCHANGE_DIRECT,DirectConfig.KEY_QUEUE_ONE,coupon);
        rabbitTemplate.convertAndSend(DirectConfig.EXCHANGE_DIRECT,DirectConfig.KEY_QUEUE_TWO,coupon);
    }

    @Test
    public void topic(){
        Coupon coupon=new Coupon();
        coupon.setId(10L);
        coupon.setStock(200);
        coupon.setName("工商银行100元通用券");
        rabbitTemplate.convertAndSend(TopicConfig.EXCHANGE_TOPIC,"bird.bird.aaa.bbb",coupon);
        rabbitTemplate.convertAndSend(TopicConfig.EXCHANGE_TOPIC,"aaa.bbb.bird.bird",coupon);
    }
}
