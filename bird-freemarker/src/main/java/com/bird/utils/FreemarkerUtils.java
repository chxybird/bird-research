package com.bird.utils;

import com.bird.entity.Card;
import com.bird.entity.User;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2021/4/13 11:43
 * @Description Freemarker工具类
 */
public class FreemarkerUtils {

    /**
     * @Author lipu
     * @Date 2021/4/13 11:44
     * @Description 输出模板文件-控制台
     * file     文件路径
     * model    数据模型
     */
    public void outputConsole(File file, Map<String,Object> model) throws Exception {
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
    public void outputFile(File file, Map<String,Object> model,String outputPath) throws Exception {
        String name = file.getName();
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

    /**
     * @Author lipu
     * @Date 2021/4/13 13:34
     * @Description freemarker文件下载
     */
//    public static void download(HttpServletResponse response,){
//
//    }

}
