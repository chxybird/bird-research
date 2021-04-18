import com.alibaba.fastjson.JSONArray;
import com.bird.AuthorityApp;
import com.bird.dao.UserDao;
import com.bird.entity.LoginUser;
import com.bird.entity.Role;
import com.bird.entity.User;
import com.bird.utils.JsonUtils;
import com.bird.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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

    @Test
    public void jwt() throws JsonProcessingException {
        String jwt="eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCQ5OUtxSlVWRnBBd1IzOHFFWTB5NVRlOENIUVFJdjBLdS4wMGM0S2tPUWxSUndvcFdtM1VkUyIsImlzT3BlbiI6MSwicGhvbmUiOiIxNTIxMjc0MDY4NyIsImlkIjoxLCJyb2xlTGlzdCI6W3siaWQiOjEsIm5hbWUiOiLotoXnuqfnrqHnkIblkZgiLCJjb2RlIjoiUk9MRV9BRE1JTiIsImRlc2NyaXB0aW9uIjoi6LaF57qn566h55CG5ZGY5oul5pyJ57O757uf55qE5omA5pyJ5p2D6ZmQIiwibG9naW5Vc2VyIjpudWxsLCJwZXJtaXNzaW9uTGlzdCI6W119XSwiZXhwIjoxNjE4NTg2MjkyLCJlbWFpbCI6IjI0NTAxMDc0OTNAcXEuY29tIiwidXNlcm5hbWUiOiLmnY7nkp4ifQ.vO1RRWEJkEzTpOxydjbp-NmVkoKPq6raZ2qPH80um0I";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Base64.getMimeEncoder().encodeToString("BIRD".getBytes())).parseClaimsJws(jwt);
        Claims body = claimsJws.getBody();
        Object roleList =body.get("roleList");
//        ObjectMapper objectMapper=new ObjectMapper();
//        String token = objectMapper.writeValueAsString(roleList);
//        System.out.println(token);
//        List<Role> list = objectMapper.readValue(token, new TypeReference<List<Role>>() {
//        });
        String json = JsonUtils.entityToJson(roleList);
        System.out.println(json);
        List<Role> list = JsonUtils.jsonToList(json, Role.class);
        System.out.println(list);
//        System.out.println(list.get(0) instanceof Map);
//        list.forEach(item->{
//            System.out.println(item.getCode());
//        });

    }

    @Test
    public void test99(){
        String json="[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"username\": \"张三\",\n" +
                "        \"password\": \"123456\",\n" +
                "        \"phone\": 15212740687,\n" +
                "        \"isOpen\": 1,\n" +
                "        \"email\": \"2450107493@qq.com\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"username\": \"李四\",\n" +
                "        \"password\": \"123456\",\n" +
                "        \"phone\": 15212740687,\n" +
                "        \"isOpen\": 1,\n" +
                "        \"email\": \"2450107493@qq.com\"\n" +
                "    }\n" +
                "]";
//        List<User> userList = JsonUtils.jsonToCollection(json, List.class, User.class);
//        System.out.println(userList.get(0) instanceof User);

    }




}
