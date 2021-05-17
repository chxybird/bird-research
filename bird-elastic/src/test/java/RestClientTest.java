import com.bird.ElasticStackApp;
import com.bird.utils.HighLevelUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author lipu
 * @Date 2021/5/8 14:48
 * @Description
 */
@SpringBootTest(classes = ElasticStackApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RestClientTest {

    @Test
    public void test(){
//        HighLevelUtils.initIndex("test-index");
//        System.out.println(HighLevelUtils.exist("test-index"));
        HighLevelUtils.deleteIndex("student");
//        System.out.println(HighLevelUtils.exist("test-index"));
//        HighLevelUtils.getIndex("test-index");

    }
}
