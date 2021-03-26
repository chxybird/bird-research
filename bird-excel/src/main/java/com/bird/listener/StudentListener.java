package com.bird.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bird.entity.Student;

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
