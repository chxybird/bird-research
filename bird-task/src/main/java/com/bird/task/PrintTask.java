package com.bird.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author lipu
 * @Date 2021/4/2 11:03
 * @Description 定时打印任务
 */
@Component
public class PrintTask {
    /**
     * @Author lipu
     * @Date 2021/4/2 11:22
     * @Description 每5秒执行一次控制台输出操作
     */
    @Scheduled(cron = "*/5 * * * * *" )
    public void printTask(){
        System.out.println("日志输出");
    }
}
