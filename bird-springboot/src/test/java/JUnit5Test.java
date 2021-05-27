import com.bird.SpringBootApp;
import com.bird.config.BirdConfig;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/5/25 16:57
 * @Description JUnit5单元测试
 */
@SpringBootTest(classes = SpringBootApp.class)
public class JUnit5Test {
    @Resource
    BirdConfig birdConfig;

    /**
     * @Author lipu
     * @Date 2021/5/25 17:00
     * @Description 前置处理
     */
    @BeforeEach
    @DisplayName("前置处理")
    public void before(){
        System.out.println("前置处理");
    }

    /**
     * @Author lipu
     * @Date 2021/5/25 17:00
     * @Description 后置处理
     */
    @AfterEach
    @DisplayName("后置处理")
    public void after(){
        System.out.println("后置处理");
    }



    @Test
    @DisplayName("快速入门案例")
    public void test1() {
        //注意此处的注解@Test与JUnit4的@Test不是一个注解
        System.out.println("快速入门");
        System.out.println(birdConfig.getName());

    }

    @DisplayName("重复测试")
    @RepeatedTest(2)
    public void test2(){
        System.out.println("打印");
    }
}
