package com.bird.lambda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author lipu
 * @Date 2021/1/23 13:27
 * @Description lambda表达式
 */
public class Client {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        //传统写法
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行了");
            }
        });
        //使用lambda表达式
        threadPool.submit(()->{
            System.out.println("线程执行了");
        });
    }
}
