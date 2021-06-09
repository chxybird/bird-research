package com.bird.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.checkerframework.checker.units.qual.C;

/**
 * @Author lipu
 * @Date 2021/6/9 16:15
 * @Description HDFS工具类
 */
public class HdfsUtils {

    private static Configuration configuration;

    /**
     * @Author lipu
     * @Date 2021/6/9 16:39
     * @Description 初始化
     */
    static {
        configuration=new Configuration();
//        FileSystem fileSystem=FileSystem.get()
    }



    /**
     * @Author lipu
     * @Date 2021/6/9 16:36
     * @Description 创建目录
     */
    public static void mkdir(){

    }

}
