package com.bird.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.bird.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/4/9 9:06
 * @Description excel工具类
 */
@Component
@Slf4j
public class ExcelUtils {
    /**
     * 默认导出excel文件名称
     */
    private final String EXCEL_NAME = "信息表.xlsx";

    /**
     * @Author lipu
     * @Date 2021/4/9 9:11
     * @Description 导出excel
     */
    public void download(HttpServletResponse response, List<?> data) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode(EXCEL_NAME, "utf-8"));
        //将数据写入到excel中并且写入到响应流中
        ServletOutputStream outputStream = response.getOutputStream();
        //获取工作薄写对象
        ExcelWriterBuilder excelWriter = EasyExcel.write(outputStream, Student.class);
        //具体工作表对象 不指定具体的序号默认为第一张
        ExcelWriterSheetBuilder sheet = excelWriter.sheet();
        //写入数据 (工作簿对象指定的是流则写入到流)
        sheet.doWrite(data);
    }

    /**
     * @Author lipu
     * @Date 2021/4/9 9:27
     * @Description 导入excel
     */
    public void upload(MultipartFile file, Class<?> clazz, AnalysisEventListener<?> listener){
        try {
            InputStream inputStream = file.getInputStream();
            ExcelReaderBuilder builder=EasyExcel.read(inputStream,clazz,listener);
            //获取具体的工作表Sheet
            ExcelReaderSheetBuilder sheet = builder.sheet();
            //读取工作表内容
            sheet.doRead();
        } catch (Exception e) {
            log.info("导入excel失败");
        }
    }

}
