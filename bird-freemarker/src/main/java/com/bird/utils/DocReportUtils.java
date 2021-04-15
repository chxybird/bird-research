package com.bird.utils;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author lipu
 * @Date 2021/4/13 10:48
 * @Description XDocReport
 */
@Component
public class DocReportUtils {
    /**
     * report对象key
     */
    public static final String REPORT = "report";
    /**
     * context对象key
     */
    public static final String CONTEXT = "context";

    /**
     * 容器
     */
    public static Map<String, Object> docMap = new HashMap<>();
    /**
     * 默认导出下载文件名称
     */
    public static final String WORD_NAME = "bird_word.docx";

    /**
     * @Author lipu
     * @Date 2021/4/13 15:37
     * @Description 清除数据
     */
    public static void flush(){
        docMap.clear();
    }

    /**
     * @Author lipu
     * @Date 2021/4/13 14:08
     * @Description 初始化 report/context
     */
    public static void init(File file) throws Exception {
        //清除数据
        flush();
        //获取word模板文件的地址
        InputStream inputStream = new FileInputStream(file);
        //freemarker加载word模板
        IXDocReport report = XDocReportRegistry.getRegistry()
                .loadReport(inputStream, TemplateEngineKind.Freemarker);
        IContext context = report.createContext();
        docMap.put(REPORT, report);
        docMap.put(CONTEXT, context);
    }

    /**
     * @Author lipu
     * @Date 2021/4/13 13:37
     * @Description 生成word并输出到文件
     */
    public static void outputFile(String outputPath) throws Exception {
        IXDocReport report = (IXDocReport) docMap.get(REPORT);
        IContext context = (IContext) docMap.get(CONTEXT);
        OutputStream outputStream = new FileOutputStream(new File(outputPath));
        report.process(context, outputStream);
    }

    /**
     * @Author lipu
     * @Date 2021/4/13 13:49
     * @Description 简单对象模型
     */
    public static void set(String key, Object object) throws XDocReportException {
        IXDocReport report = (IXDocReport) docMap.get(REPORT);
        //设置域
        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
        fieldsMetadata.load(key, object.getClass());
        //域填充
        IContext context = (IContext) docMap.get(CONTEXT);
        context.put(key, object);
    }

    /**
     * @Author lipu
     * @Date 2021/4/13 13:49
     * @Description List数据模型
     */
    public static void setList(String key, List<?> list) throws Exception {
        IXDocReport report = (IXDocReport) docMap.get(REPORT);
        //设置域
        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
        fieldsMetadata.load(key, list.getClass(), true);
        //域填充
        IContext context = (IContext) docMap.get(CONTEXT);
        context.put(key, list);
    }

    /**
     * @Author lipu
     * @Date 2021/4/13 13:49
     * @Description Set数据模型
     */
    public static void setSet(String key, Set<?> set) throws Exception {
        IXDocReport report = (IXDocReport) docMap.get(REPORT);
        //设置域
        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
        fieldsMetadata.load(key, set.getClass(), true);
        //域填充
        IContext context = (IContext) docMap.get(CONTEXT);
        context.put(key, set);
    }

    /**
     * @Author lipu
     * @Date 2021/4/13 13:49
     * @Description Map数据模型
     */
    public static void setMap(String key, Map<String, ?> map) throws Exception {
        IXDocReport report = (IXDocReport) docMap.get(REPORT);
        //设置域
        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
        fieldsMetadata.load(key, map.getClass(), true);
        //域填充
        IContext context = (IContext) docMap.get(CONTEXT);
        context.put(key, map);
    }

    /**
     * @Author lipu
     * @Date 2021/4/13 15:03
     * @Description word文件下载
     */
    public static void download(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode(WORD_NAME, "utf-8"));
        OutputStream outputStream=response.getOutputStream();
        IXDocReport report = (IXDocReport) docMap.get(REPORT);
        IContext context = (IContext) docMap.get(CONTEXT);
        report.process(context,outputStream);
    }


}
