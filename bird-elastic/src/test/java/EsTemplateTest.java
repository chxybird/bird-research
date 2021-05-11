import com.bird.ElasticStackApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/5/11 17:33
 * @Description
 */
@SpringBootTest(classes = ElasticStackApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EsTemplateTest {
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void test(){

    }
}
