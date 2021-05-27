import com.bird.MongoDB;
import com.bird.dao.StudentMongoDao;
import com.bird.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author lipu
 * @Date 2021/5/26 10:50
 * @Description
 */
@SpringBootTest(classes = MongoDB.class)
public class MongoTest {
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private StudentMongoDao studentMongoDao;



    /**
     * @Author lipu
     * @Date 2021/5/26 14:06
     * @Description 添加文档
     */
    @Test
    public void test1() {
        Student student=new Student();
        student.setId(2L);
        student.setName("test1");
        student.setSex("女");
        student.setAge(24);
        student.setEnglishGrade(130);
        student.setMathGrade(140);
        student.setChineseGrade(100);
        studentMongoDao.insert(student);

    }

    /**
     * @Author lipu
     * @Date 2021/5/26 14:33
     * @Description 批量添加
     */
    @Test
    public void test2(){
        Student s1=new Student();
        s1.setId(10L);
        Student s2=new Student();
        s2.setId(11L);
        List<Student> studentList=new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentMongoDao.insert(studentList);
    }

    /**
     * @Author lipu
     * @Date 2021/5/26 15:44
     * @Description 删除文档
     */
    @Test
    public void test3(){
        //根据主键删除
        studentMongoDao.deleteById(10L);
        //根据实体删除
        Student student=new Student();
        student.setId(11L);
        studentMongoDao.delete(student);
        //批量删除
        List<Student> studentList=new ArrayList<>();
        Student s1=new Student();
        Student s2=new Student();
        s1.setId(1L);
        s2.setId(2L);
        studentList.add(s1);
        studentList.add(s2);
        studentMongoDao.deleteAll(studentList);
        //删除所有
        studentMongoDao.deleteAll();
    }

    /**
     * @Author lipu
     * @Date 2021/5/26 16:01
     * @Description 更新文档
     */
    @Test
    public void test4(){
        //save方法如果主键在数据库存在就更新,没有就插入。
        // 更新是全量更新，字段为null都会被更新为null
        Student student=new Student();
        student.setId(2L);
        student.setName("bird");
        studentMongoDao.save(student);
        //批量更新 这里不模拟数据演示了
        List<Student> studentList=new ArrayList<>();
        studentMongoDao.saveAll(studentList);
    }

    /**
     * @Author lipu
     * @Date 2021/5/26 16:22
     * @Description 文档查询 简单查询
     */
    @Test
    public void test5(){
        //统计集合中的文档数量
        long count = studentMongoDao.count();
        System.out.println(count);
        //查询所有文档
        List<Student> allList = studentMongoDao.findAll();
        System.out.println(allList);
        //根据主键查询文档
        Optional<Student> optional = studentMongoDao.findById(1L);
        Student student = optional.get();
        System.out.println(student);
        //批量查询
        List<Long> idList=new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(3L);
        Iterable<Student> batchList = studentMongoDao.findAllById(idList);
        System.out.println(batchList);
    }

    /**
     * @Author lipu
     * @Date 2021/5/26 16:29
     * @Description 分页 排序
     */
    @Test
    public void test6(){
        //分页 第一页 每页两个数据
        System.out.println("分页");
        Page<Student> pageList = studentMongoDao.findAll(PageRequest.of(0, 2));
        pageList.forEach(System.out::println);
        System.out.println(pageList);
        //排序 根据年龄升序
        System.out.println("排序");
        List<Student> sortList = studentMongoDao.findAll(Sort.by("age").ascending());
        sortList.forEach(System.out::println);
        //分页加排序
        System.out.println("分页+排序");
        PageRequest page = PageRequest.of(0, 2, Sort.by("age").descending());
        Page<Student> pageSortList = studentMongoDao.findAll(page);
        pageSortList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/26 16:46
     * @Description 条件查询
     */
    @Test
    public void test7(){

    }


}
