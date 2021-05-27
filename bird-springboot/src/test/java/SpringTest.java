import com.bird.SpringBootApp;
import com.bird.config.BirdConfig;
import com.bird.config.BirdProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/5/24 18:14
 * @Description
 */
@SpringBootTest(classes = SpringBootApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
    @Resource
    private BirdProperties birdProperties;
    @Resource
    private BirdConfig birdConfig;

    /**
     * @Author lipu
     * @Date 2021/5/24 18:15
     * @Description @PropertySource
     */
    @Test
    public void test1(){
        String name = birdProperties.getName();
        System.out.println(name);
    }

    /**
     * @Author lipu
     * @Date 2021/5/24 20:18
     * @Description @ConfigurationProperties
     */
    @Test
    public void test2(){
        String name = birdConfig.getName();
        Integer port = birdConfig.getPort();
        System.out.println(name);
        System.out.println(port);
    }
}
