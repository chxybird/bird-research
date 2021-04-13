package com.bird;

import com.bird.entity.Card;
import com.bird.entity.Student;
import com.bird.entity.User;
import com.bird.utils.DocReportUtils;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.XDocReport;
import fr.opensagres.xdocreport.document.images.ClassPathImageProvider;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2021/3/30 20:20
 * @Description
 */
@SpringBootTest(classes = FreeMarkerApp.class)
@RunWith(SpringRunner.class)
public class FreeMarkerTest {

    /**
     * @Author lipu
     * @Date 2021/3/30 20:21
     * @Description 文件模板
     */
    @Test
    public void test1() throws IOException, TemplateException {
        //创建Freemarker配置类
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        //指定模板文件路径 类路径
        ClassPathResource pathResource = new ClassPathResource("template");
        File filePath = new File(pathResource.getFile().getPath());
        //指定模板加载器 文件模板加载器
        TemplateLoader templateLoader = new FileTemplateLoader(filePath);
        configuration.setTemplateLoader(templateLoader);
        //获取文件模板
        Template template = configuration.getTemplate("index.ftl");
        //构造数据模型
        Map<String, Object> model = new HashMap<>(16);
        Student student1 = new Student(16036024L, "张三", 24);
        Student student2 = new Student(16036025L, "李四", 25);
        Student student3 = new Student(16036026L, "王五", 26);
        Student student4 = new Student(16036027L, "赵六", 27);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        model.put("studentList", studentList);
        model.put("flag", 2);
        //生成模板
        template.process(model, new PrintWriter(System.out));
    }

    /**
     * @Author lipu
     * @Date 2021/4/4 17:03
     * @Description 字符串模板
     */
    @Test
    public void test2() throws IOException, TemplateException {
        //创建配置类
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        //指定字符串模板加载器
        TemplateLoader templateLoader = new StringTemplateLoader();
        configuration.setTemplateLoader(templateLoader);
        //创建字符串模板
        String content = "欢迎您,${username}";
        Template template = new Template("bird", new StringReader(content), configuration);
        //构造数据模型
        Map<String, Object> model = new HashMap<>();
        model.put("username", "小鸟程序员");
        //生成模板
        template.process(model, new PrintWriter(System.out));
    }

    /**
     * @Author lipu
     * @Date 2021/4/12 15:17
     * @Description freemarker与word文档的结合调研
     */
    @Test
    public void test3() throws IOException, XDocReportException {
        //读取类路径下的word模板文件
        ClassPathResource path = new ClassPathResource("bird.docx");
        //流加载word模板
        InputStream inputStream = path.getInputStream();
        //freemarker加载word模板
        IXDocReport report = XDocReportRegistry.getRegistry()
                .loadReport(inputStream, TemplateEngineKind.Freemarker);
        //设置域
        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
        //设置单数据域
        fieldsMetadata.load("user", User.class);
        //设置集合数据域
        fieldsMetadata.load("cardList", Card.class, true);
        //设置图片域
        fieldsMetadata.addFieldAsImage("logo");
        //数据填充
        User user=new User(1L,"张三",24);
        List<Card> cardList=new ArrayList<>();
        cardList.add(new Card(1L,"招商银行卡"));
        cardList.add(new Card(2L,"建设银行卡"));
        cardList.add(new Card(3L,"邮政银行卡"));
        cardList.add(new Card(4L,"农业银行卡"));
//        IImageProvider logo=new ClassPathImageProvider(Object.class.getClassLoader(),"卡通.jpg");
        IImageProvider logo=new FileImageProvider(new File("E:\\Down_Cache\\wpcache\\cdn-ali-img.birdpaper.cn\\bizhi\\staticwp\\201408\\169bf0541286cae1e687700fbd3d52251401.jpg"));
        IContext context = report.createContext();
        context.put("user",user);
        context.put("cardList",cardList);
        context.put("logo",logo);
        OutputStream outputStream=new FileOutputStream(new File("F:\\Data","my.docx"));
        report.process(context,outputStream);
    }

    /**
     * @Author lipu
     * @Date 2021/4/13 13:58
     * @Description 工具类测试
     */
    @Test
    public void test4() throws Exception {
        //初始化
        DocReportUtils.init(new File("F:\\", "bird.docx"));
        //设置数据
        User user=new User(16036024L,"小鸟",24);
        DocReportUtils.set("user",user);
        List<Card> cardList=new ArrayList<>();
        cardList.add(new Card(1L,"招商银行卡"));
        cardList.add(new Card(2L,"建设银行卡"));
        cardList.add(new Card(3L,"邮政银行卡"));
        cardList.add(new Card(4L,"农业银行卡"));
        DocReportUtils.setList("cardList",cardList);
        //输出word文件
        DocReportUtils.outputFile("F:\\test\\bird.docx");
    }

}

