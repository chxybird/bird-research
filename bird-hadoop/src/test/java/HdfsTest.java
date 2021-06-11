import com.bird.HadoopApp;
import com.bird.utils.HdfsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author lipu
 * @Date 2021/6/10 9:13
 * @Description
 */
@SpringBootTest(classes = HadoopApp.class)
public class HdfsTest {

    /**
     * @Author lipu
     * @Date 2021/6/10 9:14
     * @Description 创建目录
     */
    @Test
    public void test1(){
        HdfsUtils.mkdir("/test");
    }

    /**
     * @Author lipu
     * @Date 2021/6/10 15:55
     * @Description 删除目录
     */
    @Test
    public void test2(){
        HdfsUtils.rm("/kk");
    }

    /**
     * @Author lipu
     * @Date 2021/6/10 15:58
     * @Description 判断目录是否存在
     */
    @Test
    public void test3(){
        boolean exists = HdfsUtils.exists("/test");
        System.out.println(exists);
    }
}
