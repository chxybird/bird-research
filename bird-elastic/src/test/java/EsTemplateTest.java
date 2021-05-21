import com.bird.ElasticStackApp;
import com.bird.entity.Student;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.ParsedMin;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2021/5/11 17:33
 * @Description
 */
@SpringBootTest(classes = ElasticStackApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EsTemplateTest {
    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;


    /**
     * @Author lipu
     * @Date 2021/5/17 13:40
     * @Description 查询所有matchAll
     */
    @Test
    public void test1() {
        //创建条件构造器构造者对象
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //使用构造者构造条件构造器
        NativeSearchQuery matchAllQuery = builder.withQuery(QueryBuilders.matchAllQuery()).build();
        //查询获得结果集
        SearchHits<Student> result = elasticsearchTemplate.search(matchAllQuery, Student.class);
        List<SearchHit<Student>> searchHits = result.getSearchHits();
        //处理结果集
        List<Student> studentList = new ArrayList<>();
        searchHits.forEach(item -> {
            Student student = item.getContent();
            studentList.add(student);
        });
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/17 13:43
     * @Description 分词查询match
     */
    @Test
    public void test2() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //分词查询中会将搜索关键词进行分词再进行查询
        Query matchQuery = builder.withQuery(QueryBuilders.matchQuery("description", "目前处于失业状态")).build();
        SearchHits<Student> result = elasticsearchTemplate.search(matchQuery, Student.class);
        List<SearchHit<Student>> searchHits = result.getSearchHits();
        //处理结果集
        List<Student> studentList = new ArrayList<>();
        searchHits.forEach(item -> {
            Student student = item.getContent();
            studentList.add(student);
        });
        studentList.forEach(System.out::println);

    }

    /**
     * @Author lipu
     * @Date 2021/5/17 13:44
     * @Description 精准查询term
     */
    @Test
    public void test3() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //精准查询搜索关键词不会被分词
        NativeSearchQuery termQuery = builder.withQuery(QueryBuilders.termQuery("description", "目前处于失业状态")).build();
        SearchHits<Student> result = elasticsearchTemplate.search(termQuery, Student.class);
        List<SearchHit<Student>> searchHits = result.getSearchHits();
        //处理结果集
        List<Student> studentList = new ArrayList<>();
        searchHits.forEach(item -> {
            Student student = item.getContent();
            studentList.add(student);
        });
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/17 14:26
     * @Description 范围查询
     */
    @Test
    public void test4() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //范围查询 类似于sql的between
        NativeSearchQuery rangeQuery = builder.withQuery(QueryBuilders
                .rangeQuery("englishGrade").gte(90).lte(120)).build();
        SearchHits<Student> result = elasticsearchTemplate.search(rangeQuery, Student.class);
        List<SearchHit<Student>> searchHits = result.getSearchHits();
        //处理结果集
        List<Student> studentList = new ArrayList<>();
        searchHits.forEach(item -> {
            Student student = item.getContent();
            studentList.add(student);
        });
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/17 15:14
     * @Description 通配符查询
     */
    @Test
    public void test5() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //通配符查询 *匹配多个字符 #匹配单个字符
        NativeSearchQuery wildcardQuery = builder.withQuery(QueryBuilders
                .wildcardQuery("name", "小*")).build();
        SearchHits<Student> result = elasticsearchTemplate.search(wildcardQuery, Student.class);
        List<SearchHit<Student>> searchHits = result.getSearchHits();
        //处理结果集
        List<Student> studentList = new ArrayList<>();
        searchHits.forEach(item -> {
            Student student = item.getContent();
            studentList.add(student);
        });
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/17 15:23
     * @Description 多条件查询
     */
    @Test
    public void test6() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //多条件查询 should是应该满足的条件 must是必须满足的条件 must not 必须不满足的条件
        NativeSearchQuery searchQuery = builder.withQuery(QueryBuilders.boolQuery()
                //必须不为女
                .mustNot(QueryBuilders.termQuery("sex", "女"))
                //年龄必须大于20岁(不包含)
                .must(QueryBuilders.rangeQuery("age").gt(20))).build();
        SearchHits<Student> result = elasticsearchTemplate.search(searchQuery, Student.class);
        List<SearchHit<Student>> searchHits = result.getSearchHits();
        //处理结果集
        List<Student> studentList = new ArrayList<>();
        searchHits.forEach(item -> {
            Student student = item.getContent();
            studentList.add(student);
        });
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/17 15:24
     * @Description 排序+分页查询
     */
    @Test
    public void test7() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //多条件查询 should是应该满足的条件 must是必须满足的条件 must not 必须不满足的条件
        NativeSearchQuery searchQuery = builder
                .withQuery(QueryBuilders.matchAllQuery())
                //根据年龄升序排序
                .withSort(SortBuilders.fieldSort("age").order(SortOrder.ASC))
                //第二页 每页展示两个数据
                .withPageable(PageRequest.of(1, 2))
                .build();
        SearchHits<Student> result = elasticsearchTemplate.search(searchQuery, Student.class);
        List<SearchHit<Student>> searchHits = result.getSearchHits();
        //处理结果集
        List<Student> studentList = new ArrayList<>();
        searchHits.forEach(item -> {
            Student student = item.getContent();
            studentList.add(student);
        });
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/17 15:43
     * @Description 高亮查询
     */
    @Test
    public void test8() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        NativeSearchQuery searchQuery = builder
                .withQuery(QueryBuilders.matchQuery("description", "软件公司"))
                .withHighlightBuilder(new HighlightBuilder()
                        .field("description")
                        .preTags("<font color='#dd4b39'>")
                        .postTags("</font>").requireFieldMatch(false))
                .build();
        SearchHits<Student> result = elasticsearchTemplate.search(searchQuery, Student.class);
        List<SearchHit<Student>> searchHits = result.getSearchHits();
        //处理结果集 高亮结果集需要自行处理
        List<Student> studentList = new ArrayList<>();
        searchHits.forEach(item -> {
            //获取高亮的内容
            Map<String, List<String>> highlightFields = item.getHighlightFields();
            //将高亮的内容进行填充
            Student student = item.getContent();
            student.setDescription(highlightFields.get("description").get(0));
            studentList.add(student);
        });
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/17 16:41
     * @Description 聚合查询 指标+桶=结果集
     */
    @Test
    public void test9() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        NativeSearchQuery searchQuery = builder
                //添加聚合指标 求和指标 最大值指标 一般指标(只做聚合)
                .addAggregation(AggregationBuilders.sum("totalMathGrade").field("mathGrade"))
                .addAggregation(AggregationBuilders.max("maxAge").field("age"))
                .addAggregation(AggregationBuilders.terms("sexAggregation").field("sex")
                        //添加子指标
                        .subAggregation(AggregationBuilders.avg("sexAggregationChineseGradeAvg").field("chineseGrade"))
                        .subAggregation(AggregationBuilders.min("sexAggregationEnglishGradeMin").field("englishGrade"))
                )
                .build();
        SearchHits<Student> searchHits = elasticsearchTemplate.search(searchQuery, Student.class);
        //获取指标结果或者桶
        Aggregations aggregations = searchHits.getAggregations();
        if (aggregations != null) {
            //获取totalMathGrade指标结果
            ParsedSum totalMathGrade = aggregations.get("totalMathGrade");
            //结果集 type 指标类型 value指标值 name自定义的指标名称
            System.out.println(totalMathGrade.getType());
            System.out.println(totalMathGrade.getValue());
            System.out.println(totalMathGrade.getName());
            //获取maxAge指标结果
            Map<String, Aggregation> map = aggregations.getAsMap();
            ParsedMax maxAge = (ParsedMax) map.get("maxAge");
            System.out.println("指标类型为:" + maxAge.getType() + "指标为:" + maxAge.getName() + "指标值为:" + maxAge.getValue());
            //获取sexAggregation聚合指标
            ParsedTerms sexAggregation = (ParsedTerms) map.get("sexAggregation");
            //获取sexAggregation的桶结果集
            List<? extends Terms.Bucket> bucketList = sexAggregation.getBuckets();
            bucketList.forEach(item -> {
                ParsedAvg sexAggregationChineseGradeAvg = item.getAggregations().get("sexAggregationChineseGradeAvg");
                ParsedMin sexAggregationEnglishGradeMin = item.getAggregations().get("sexAggregationEnglishGradeMin");
                System.out.println(sexAggregationChineseGradeAvg.getName() + sexAggregationChineseGradeAvg.getValue());
                System.out.println(sexAggregationEnglishGradeMin.getName() + sexAggregationEnglishGradeMin.getValue());
            });

        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/18 9:15
     * @Description 多条件查询 补1 should用法 等价于或者
     */
    @Test
    public void test10() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //年龄为23 或者 年龄为24的学生信息
        NativeSearchQuery searchQuery = builder.withQuery(QueryBuilders.boolQuery()
                //年龄为23
                .should(QueryBuilders.termQuery("age", 23))
                //年龄为24
                .should(QueryBuilders.termQuery("age",22))).build();
        SearchHits<Student> result = elasticsearchTemplate.search(searchQuery, Student.class);
        List<SearchHit<Student>> searchHits = result.getSearchHits();
        //处理结果集
        List<Student> studentList = new ArrayList<>();
        searchHits.forEach(item -> {
            Student student = item.getContent();
            studentList.add(student);
        });
        studentList.forEach(System.out::println);
    }

    /**
     * @Author lipu
     * @Date 2021/5/18 17:20
     * @Description 多条件查询 补2 should+must的正确用法
     * 使用should作为一个条件与多个should组合成多条件
     */
    @Test
    public void test11() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //年龄必须为20且数学成绩大于100的学生 或者 所有女学生
        NativeSearchQuery searchQuery = builder.withQuery(QueryBuilders.boolQuery()
                .should(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("sex","女")))
                .should(QueryBuilders.boolQuery()
                        .must(QueryBuilders.rangeQuery("mathGrade").gt(100))
                        .must(QueryBuilders.termQuery("age",20)))
               ).build();
        SearchHits<Student> result = elasticsearchTemplate.search(searchQuery, Student.class);
        List<SearchHit<Student>> searchHits = result.getSearchHits();
        //处理结果集
        List<Student> studentList = new ArrayList<>();
        searchHits.forEach(item -> {
            Student student = item.getContent();
            studentList.add(student);
        });
        studentList.forEach(System.out::println);
    }


}
