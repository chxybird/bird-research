import com.bird.ElasticStackApp;
import com.bird.dao.EsStudentDao;
import com.bird.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author lipu
 * @Date 2021/5/11 9:12
 * @Description
 */
@SpringBootTest(classes = ElasticStackApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EsDaoTest {

    @Resource
    private EsStudentDao esStudentDao;


    /**
     * @Author lipu
     * @Date 2021/5/11 17:39
     * @Description 查询索引库中文档的数量
     */
    @Test
    public void count(){
        long count = esStudentDao.count();
        System.out.println(count);
    }

    /**
     * @Author lipu
     * @Date 2021/5/11 9:20
     * @Description 添加一条文档
     */
    @Test
    public void addOne(){
        //添加一条数据 如果不指定ID,ES会随机生成ID
        esStudentDao.save(new Student(null,"王五",20,"男",120,110,150, new Date(),"王五今年20岁,目前工作在杭州一家软件公司,方向是大数据"));
    }

    @Test
    public void addBatch(){
        //批量添加数据
        List<Student> studentList=new ArrayList<>(10);
        studentList.add(new Student(null,"李四",23,"男",110,128,141, new Date(),"李四今年23岁,目前工作在上海一家软件公司,方向是JAVA开发"));
        studentList.add(new Student(null,"小红",20,"女",92,145,125, new Date(),"小红今年20岁,目前工作在北京一家软件公司,方向是WEB前端工程师"));
        //不指定主键会随机生成主键
        studentList.add(new Student(null,"小莉",22,"女",100,115,125, new Date(),"小莉今年22岁,目前工作在广州一家软件公司,方向是UI"));
        esStudentDao.saveAll(studentList);
    }

    /**
     * @Author lipu
     * @Date 2021/5/11 14:01
     * @Description 根据ID修改文档
     */
    @Test
    public void updateDocument(){
        //ES的修改文档和添加文档使用相同的API,根据主键全量覆盖 先查询再修改
        Student student = new Student();
        student.setId("");
        student.setName("小鸟");
        esStudentDao.save(student);
    }

    /**
     * @Author lipu
     * @Date 2021/5/11 14:15
     * @Description 文档删除操作
     */
    @Test
    public void delete(){
        //根据主键删除文档
        esStudentDao.deleteById("");
        //根据实体删除文档(需要主键)
        Student student=new Student();
        student.setId("");
        esStudentDao.delete(student);
        //批量删除
        List<Student> studentList=new ArrayList<>();
        Student s1=new Student();
        Student s2=new Student();
        s1.setId("");
        s2.setId("");
        studentList.add(s1);
        studentList.add(s2);
        esStudentDao.deleteAll(studentList);
        //删除所有
        esStudentDao.deleteAll();
    }

    /**
     * @Author lipu
     * @Date 2021/5/11 10:53
     * @Description 文档简单查询
     */
    @Test
    public void query(){
        //根据ID查询文档信息
        Optional<Student> optional = esStudentDao.findById("");
        optional.ifPresent(System.out::println);
        //根据ID判断文档是否存在
        boolean exists = esStudentDao.existsById("");
        System.out.println(exists);
        //查询所有文档信息
        Iterable<Student> iterable = esStudentDao.findAll();
        iterable.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/11 16:12
     * @Description 分页查询
     */
    @Test
    public void queryPage(){
        //设置分页条件 JPA分页从0开始
        Pageable pageable= PageRequest.of(0,2);
        Page<Student> studentPage = esStudentDao.findAll(pageable);
        long totalElements = studentPage.getTotalElements();
        int totalPages = studentPage.getTotalPages();
        List<Student> studentList = studentPage.getContent();
        studentList.forEach(System.out::println);
        System.out.println("总记录数"+totalElements);
        System.out.println("总页数"+totalPages);
    }

    /**
     * @Author lipu
     * @Date 2021/5/11 16:12
     * @Description 排序查询
     */
    @Test
    public void querySort(){
        //根据数学成绩降序排序
        Iterable<Student> mathDesc = esStudentDao.findAll(Sort.by("mathGrade").descending());
        //根据语文成绩升序查询
        Iterable<Student> chineseASC = esStudentDao.findAll(Sort.by("chineseGrade").ascending());
        //多条件排序,先根据年龄升序排序,再根据数学成绩降序排序
        Iterable<Student> iterable = esStudentDao.findAll(Sort.by("age").ascending().and(Sort.by("mathGrade").descending()));
        System.out.println("数学成绩降序排序");
        mathDesc.forEach(System.out::println);
        System.out.println("语文成绩升序查询");
        chineseASC.forEach(System.out::println);
        System.out.println("多条件排序,先根据年龄升序排序,再根据数学成绩降序排序");
        iterable.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/11 16:39
     * @Description 分页加排序
     */
    @Test
    public void queryPageSort(){
        PageRequest pageSort = PageRequest.of(0, 2, Sort.by("age").ascending());
        Page<Student> studentPage = esStudentDao.findAll(pageSort);
        List<Student> content = studentPage.getContent();
        content.forEach(System.out::println);
    }
}
