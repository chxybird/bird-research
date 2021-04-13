package com.bird.utils;

import com.bird.entity.Card;
import com.bird.entity.User;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.springframework.stereotype.Component;

import java.io.*;
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
     * @Author lipu
     * @Date 2021/4/13 14:08
     * @Description 初始化 report/context
     */
    public static Map<String, Object> init(File file) throws Exception {
        //获取word模板文件的地址
        InputStream inputStream = new FileInputStream(file);
        //freemarker加载word模板
        IXDocReport report = XDocReportRegistry.getRegistry()
                .loadReport(inputStream, TemplateEngineKind.Freemarker);
        IContext context = report.createContext();
        docMap.put(REPORT, report);
        docMap.put(CONTEXT, context);
        return docMap;
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
        fieldsMetadata.load(key, list.getClass(),true);
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
        fieldsMetadata.load(key, set.getClass(),true);
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
        fieldsMetadata.load(key, map.getClass(),true);
        //域填充
        IContext context = (IContext) docMap.get(CONTEXT);
        context.put(key, map);
    }


}
