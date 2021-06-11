package com.bird.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @Author lipu
 * @Date 2021/6/9 16:15
 * @Description HDFS工具类
 * 本地远程连接Hadoop系统时需要在本地配置相关的Hadoop变量,主要包括hadoop.dll与winutils.exe等
 * winutils：
 * 由于hadoop主要基于linux编写,winutil.exe主要用于模拟linux下的目录环境。
 * 当Hadoop在windows下运行或调用远程Hadoop集群的时候,需要该辅助程序才能运行。
 * 不需要下载安装windows版本的Hadoop,只需要引入winutils.exe、配置一下环境变量即可。
 */
@Slf4j
public class HdfsUtils {
    /* NameNode的地址 */
    private static final String IP_PORT = "hdfs://192.168.6.129:8020";
    /* HDFS配置的登录用户 */
    private static final String USER = "root";

    private static FileSystem fileSystem;


    /**
     * @Author lipu
     * @Date 2021/6/9 16:39
     * @Description 初始化
     */
    static {
        try {
            Configuration configuration = new Configuration();
            fileSystem = FileSystem.get(new URI(IP_PORT), configuration, USER);
        } catch (Exception e) {
            log.info("Hadoop客户端初始化失败");
        }
    }


    /**
     * @Author lipu
     * @Date 2021/6/9 16:36
     * @Description 创建目录
     */
    public static void mkdir(String path) {
        if (path == null) {
            log.info("目录不能为空!!!");
            return;
        }
        try {
            fileSystem.mkdirs(new Path(path));
        } catch (IOException e) {
            log.info("创建目录失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/6/10 16:00
     * @Description 上传文件 目录不存在自己创建 文件存在覆盖
     */
    public static void upload(MultipartFile file, String path) {
        if (path == null || file == null) {
            log.info("目录不能为空且上传的文件不能为空");
            return;
        }
        try {
            //获取文件名
            String originalFilename = file.getOriginalFilename();
            //组装上传的地址
            Path uploadPath = new Path(IP_PORT + path + "/" + originalFilename);
            //文件流写数据
            FSDataOutputStream outputStream = fileSystem.create(uploadPath);
            InputStream inputStream = file.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] bytes=new byte[4096];
            int len=0;
            while (((len=bufferedInputStream.read(bytes)))>0){
                outputStream.write(bytes,0,len);
            }
            bufferedInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            log.info("文件上传失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/6/10 16:43
     * @Description 文件下载
     */
    public static void download(HttpServletResponse response,String path){
        try {
            //获取下载的文件名
            FileStatus fileStatus = fileSystem.getFileStatus(new Path(path));
            String name = fileStatus.getPath().getName();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename="
                    + URLEncoder.encode(name, "utf-8"));
            //读取文件为流 这里用到缓冲流
            FSDataInputStream inputStream = fileSystem.open(new Path(path));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bytes=new byte[4096];
            int len=0;
            while (((len=bufferedInputStream.read(bytes)))>0){
                outputStream.write(bytes,0,len);
            }
            bufferedInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            log.info("文件下载失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/6/10 15:20
     * @Description 删除目录或者文件(递归) 存在删除不存在不操作
     */
    public static void rm(String path) {
        if (path == null) {
            log.info("目录不能为空!!!");
            return;
        }
        try {
            fileSystem.deleteOnExit(new Path(path));
        } catch (IOException e) {
            log.info("创建目录失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/6/10 15:37
     * @Description 判断文件或者目录是否存在
     */
    public static boolean exists(String path) {
        if (path == null) {
            log.info("路径不能为空!!!");
            return false;
        }
        try {
            return fileSystem.exists(new Path(path));
        } catch (IOException e) {
            log.info("文件是否存在判断异常");
            return false;
        }
    }
}
