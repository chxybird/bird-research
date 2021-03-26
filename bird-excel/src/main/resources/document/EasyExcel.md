# EasyExcel--小鸟程序员

### 一、快速入门

##### 1.导入依赖

```xml
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>easyexcel</artifactId>
	<version>2.2.7</version>
</dependenc>
```

##### 2.构建实体类

```java
/**
 * @Author lipu
 * @Date 2021/3/22 14:00
 * @Description 学生实体类
 */
@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Integer mathGrade;
    private Integer englishGrade;
    private Integer chineseGrade;
    private Integer dId;
}
```

##### 3.编写监听器

```java
/**
 * @Author lipu
 * @Date 2021/3/24 14:09
 * @Description 自定义监听器
 */
public class StudentListener extends AnalysisEventListener<Student> {

    /**
     * 监听之后的业务处理
     * @param student           每次读取到后封装的对象
     * @param analysisContext
     */
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println(student);
    }

    /**
     * 读取完整个Excel文档之后的业务处理
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取完毕");
    }
}
```

##### 4.测试

```java
/**
 * @Author lipu
 * @Date 2021/3/24 13:50
 * @Description
 */
@SpringBootTest(classes = ExcelApp.class)
@RunWith(SpringRunner.class)
public class ExcelTest {

    /**
     * @Author lipu
     * @Date 2021/3/24 13:54
     * @Description 快速入门案例
     */
    @Test
    public void test1(){
        //获取Excel工作簿
        /**
         * 参数一:读取的Excel文件路径
         * 参数二:转换的Java实体类
         * 参数三:监听器、监听Excel读取操作
         */
        ExcelReaderBuilder builder = EasyExcel.read(
                "F:\\Data\\student.xlsx",
                Student.class,
                new StudentListener()
        );
        //获取具体的工作表Sheet
        ExcelReaderSheetBuilder sheet = builder.sheet();
        //读取工作表内容
        sheet.doRead();
    }
}
```





