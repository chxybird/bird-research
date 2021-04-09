package com.bird.service.impl;

import com.bird.dao.StudentMapper;
import com.bird.entity.Student;
import com.bird.service.StudentService;
import com.bird.utils.ExcelUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/3/26 11:55
 * @Description
 */
@Service
public class StudentImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private ExcelUtils excelUtils;

    /**
     * @Author lipu
     * @Date 2021/3/26 11:56
     * @Description 查询所有学生信息
     */
    @Override
    public List<Student> findAll() {
        return studentMapper.selectList(null);
    }

    /**
     * @Author lipu
     * @Date 2021/4/9 14:00
     * @Description 导出并下载excel
     */
    @Override
    public void download(HttpServletResponse response) throws IOException {
        List<Student> studentList = studentMapper.selectList(null);
        excelUtils.download(response,studentList);
    }

    /**
     * @Author lipu
     * @Date 2021/4/9 15:42
     * @Description 导入数据
     */
    @Override
    public void upload(MultipartFile file) {

    }
}
