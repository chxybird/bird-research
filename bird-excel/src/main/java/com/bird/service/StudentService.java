package com.bird.service;

import com.bird.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/3/26 11:54
 * @Description
 */
public interface StudentService {

    List<Student> findAll();

    void download(HttpServletResponse response) throws IOException;

    void upload(MultipartFile file);
}
