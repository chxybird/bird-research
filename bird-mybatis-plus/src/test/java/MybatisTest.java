import com.bird.MybatisPlusApp;
import com.bird.dao.StudentDao;
import com.bird.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/4/16 11:42
 * @Description
 */

@SpringBootTest(classes = MybatisPlusApp.class)
@RunWith(SpringRunner.class)
public class MybatisTest {

    @Resource
    private StudentDao studentDao;

    @Test
    public void test(){
//        Student student = studentDao.selectById(1L);
//        System.out.println(student);
        Student student = studentDao.findById(1L);
        System.out.println(student);

//        Student student = studentDao.selectById(1);
//        System.out.println(student);

    }
}
