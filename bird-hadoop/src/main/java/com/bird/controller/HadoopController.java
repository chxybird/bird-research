package com.bird.controller;

import com.bird.utils.HdfsUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author lipu
 * @Date 2021/6/10 16:04
 * @Description
 */
@RestController
@RequestMapping("/hadoop")
public class HadoopController {

    /**
     * @Author lipu
     * @Date 2021/6/10 16:05
     * @Description 上传文件到HDFS
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file){
        HdfsUtils.upload(file,"/bird");
        return "success";
    }

    /**
     * @Author lipu
     * @Date 2021/6/11 14:25
     * @Description 从HDFS下载文件
     */
    @PostMapping("/download")
    public void download(HttpServletResponse response){
        HdfsUtils.download(response,"/bird/hadoop.txt");
    }
}
