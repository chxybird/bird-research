package com.bird;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

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
     * @Description 入门测试案例
     */
    public void test1() throws IOException {
        //创建Freemarker配置类
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_29);
        ClassPathResource pathResource=new ClassPathResource("/template");
        //指定模板加载器
        TemplateLoader templateLoader=new FileTemplateLoader(new File(pathResource.getPath()));
        configuration.setTemplateLoader(templateLoader);
        //获取模板
        configuration.getTemplate("index");
        //构造数据模型
        //输出模板
    }
}
