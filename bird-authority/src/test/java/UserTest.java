import com.bird.AuthorityApp;
import com.bird.dao.UserDao;
import com.bird.entity.LoginUser;
import com.bird.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2021/4/16 11:56
 * @Description
 */
@SpringBootTest(classes = AuthorityApp.class)
@RunWith(SpringRunner.class)
public class UserTest {
    @Resource
    private UserDao userDao;

    @Test
    public void test(){
        LoginUser loginUser = userDao.findByEmail("2450107493@qq.com");
        System.out.println(loginUser);
    }


}
