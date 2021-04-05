package com.bird;

import com.entity.Student;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.HashMap;
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
        Student student = new Student(16036024L, "小鸟程序员", 24);
        //生成模板
        template.process(student, new PrintWriter(System.out));
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
        String content="欢迎您,${username}";
        Template template=new Template("bird",new StringReader(content),configuration);
        //构造数据模型
        Map<String,Object> model=new HashMap<>();
        model.put("username","小鸟程序员");
        //生成模板
        template.process(model,new PrintWriter(System.out));
    }
}
