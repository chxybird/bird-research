package com.bird.service.impl;

import com.bird.entity.Student;
import com.bird.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lipu
 * @Date 2021/3/26 11:55
 * @Description
 */
@Service
public class StudentImpl implements StudentService {

    /**
     * @Author lipu
     * @Date 2021/3/26 11:56
     * @Description 查询所有学生信息
     */
    @Override
    public List<Student> findAll() {
        return null;
    }
}
