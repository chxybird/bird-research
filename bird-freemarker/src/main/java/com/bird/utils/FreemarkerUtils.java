package com.bird.utils;


import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2021/4/13 11:43
 * @Description Freemarker工具类
 */
public class FreemarkerUtils {

    private static Configuration configuration;
    private static TemplateLoader templateLoader;

    /**
     * @Author lipu
     * @Date 2021/4/13 15:41
     * @Description 静态初始化
     */
    static {

    }

    /**
     * @Author lipu
     * @Date 2021/4/13 11:44
     * @Description 输出模板文件-控制台
     * file     文件路径
     * model    数据模型
     */
    public static void outputConsole(File file, Map<String, Object> model) throws Exception {
        //创建Freemarker配置类
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        //指定模板加载器 文件模板加载器
        TemplateLoader templateLoader = new FileTemplateLoader(new File(file.getPath()));
        configuration.setTemplateLoader(templateLoader);
        //获取文件模板
        Template template = configuration.getTemplate(file.getName());
        //生成模板 控制台输出
        template.process(model, new PrintWriter(System.out));
    }

    /**
     * @Author lipu
     * @Date 2021/4/13 11:44
     * @Description 输出模板文件-磁盘
     * file         模板路径
     * model        数据模型
     * outputPath   模板输出路径
     */
    public void outputFile(File file, Map<String, Object> model, String outputPath) throws Exception {
        //创建Freemarker配置类
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        //指定模板加载器 文件模板加载器
        TemplateLoader templateLoader = new FileTemplateLoader(new File(file.getPath()));
        configuration.setTemplateLoader(templateLoader);
        //获取文件模板
        Template template = configuration.getTemplate(file.getName());
        //生成模板
        template.process(model, new FileWriter(outputPath));
    }

}
