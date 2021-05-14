package com.bird.controller;

import com.bird.entity.Student;
import com.bird.service.StudentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/3/26 11:53
 * @Description 学生控制层
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;



    /**
     * @Author lipu
     * @Date 2021/3/26 21:28
     * @Description 查询所有学生
     */
    @GetMapping("/findAll")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    /**
     * @Author lipu
     * @Date 2021/4/9 9:02
     * @Description 导出excel报表
     */
    @PostMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        studentService.download(response);
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file){
        studentService.upload(file);
    }
}
