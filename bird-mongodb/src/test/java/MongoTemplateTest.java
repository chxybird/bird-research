import com.bird.MongoDB;
import com.bird.entity.Student;
import com.bird.vo.CalculateVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author lipu
 * @Date 2021/5/27 16:34
 * @Description mongoTemplate一般用作复杂查询使用
 */
@SpringBootTest(classes = MongoDB.class)
public class MongoTemplateTest {
    @Resource
    private MongoTemplate mongoTemplate;


    /**
     * @Author lipu
     * @Date 2021/5/27 16:52
     * @Description 条件查询
     */
    @Test
    void test1() {
        //构建查询对象
        Query query=new Query();
        //构建条件对象 查询name等于test1的文档信息
        Criteria criteria = Criteria.where("name").is("test1");
        query.addCriteria(criteria);
        List<Student> studentList = mongoTemplate.find(query,Student.class);
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/27 16:58
     * @Description 条件查询模糊匹配
     */
    @Test
    void test2(){
        Query query=new Query();
        //精准匹配
        Pattern pattern1=Pattern.compile("^test$",Pattern.CASE_INSENSITIVE);
        //左边任意 右边为test结尾
        Pattern pattern2=Pattern.compile("^.*test$",Pattern.CASE_INSENSITIVE);
        //左边为test开头 右边任意
        Pattern pattern3=Pattern.compile("^test.*$",Pattern.CASE_INSENSITIVE);
        //左右都任意
        Pattern pattern4=Pattern.compile("^.*test.*$",Pattern.CASE_INSENSITIVE);
        //单字符匹配 左边test开头 右边匹配任意一个字符
        Pattern pattern5=Pattern.compile("^test.#$",Pattern.CASE_INSENSITIVE);

        Criteria criteria = Criteria.where("name").regex(pattern5);
        query.addCriteria(criteria);
        List<Student> studentList = mongoTemplate.find(query, Student.class);
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/27 17:27
     * @Description 多条件查询 AND
     */
    @Test
    void test3(){
        Query query=new Query();
        Criteria criteria = Criteria.where("mathGrade").gt(90).and("chineseGrade").lt(60);
        query.addCriteria(criteria);
        List<Student> studentList = mongoTemplate.find(query, Student.class);
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/27 17:31
     * @Description 多条件查询 OR
     */
    @Test
    void test4(){
        Query query=new Query();
        //作为外壳使用 来组装多个条件
        Criteria criteria=new Criteria();
        //条件一
        Criteria criteriaOne = Criteria.where("name").is("test1");
        //条件二
        Criteria criteriaTwo = Criteria.where("name").is("test4");
        //组装条件
        query.addCriteria(criteria.orOperator(criteriaOne,criteriaTwo));
        List<Student> studentList = mongoTemplate.find(query, Student.class);
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/27 17:38
     * @Description 排序加分页
     */
    @Test
    void test5(){
        Query query=new Query();
        //根据年龄升序排行 分页展示第一页 每页2条数据
        query.with(PageRequest.of(0,2, Sort.by("age").ascending()));
        //不添加条件 默认无条件查询
        List<Student> studentList = mongoTemplate.find(query, Student.class);
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/27 17:46
     * @Description 忽略字段查询
     */
    @Test
    void test6(){
        Query query=new Query();
        //查询结果忽略name字段
        query.fields().exclude("name");
        List<Student> studentList = mongoTemplate.find(query, Student.class);
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/28 9:41
     * @Description 聚合查询
     * mongo中的聚合概念对应mysql的概念如下
     * project->select
     * match->where
     * group->group by
     *
     */
    @Test
    public void test7(){
        TypedAggregation<Student> aggregation = Aggregation.newAggregation(Student.class,
                //过滤条件 年龄大于18的
                Aggregation.match(Criteria.where("age").gt(18)),
                Aggregation.group("sex").max("englishGrade").as("maxEnglishGrade"));
        AggregationResults<Map> results = mongoTemplate.aggregate(aggregation, Map.class);
        results.getMappedResults().forEach(System.out::println);
    }

}
