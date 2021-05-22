package com.bird.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;


import java.util.concurrent.CountDownLatch;

/**
 * @Author lipu
 * @Date 2021/5/20 13:38
 * @Description zookeeper工具类
 */
@Slf4j
public class ZkUtils {

    private static final String IP_PORT = "192.168.6.128:2181";
    /**
     * 心跳时间
     */
    private static final Integer SESSION_TIMEOUT = 5000;

    private static ZooKeeper zooKeeper = null;

    /**
     * @Author lipu
     * @Date 2021/5/21 10:56
     * @Description 初始化连接信息
     */
    static {
        try {
            //zk的连接创建是异步的.所以使用同步工具来使连接正确的被创建
            CountDownLatch countDownLatch=new CountDownLatch(1);
            zooKeeper = new ZooKeeper(IP_PORT, SESSION_TIMEOUT, watchedEvent -> {
                //监听是否连接成功 Event.KeeperState.SyncConnected表示成功
                if (watchedEvent.getState()== Watcher.Event.KeeperState.SyncConnected){
                    log.info("zookeeper连接初始化成功");
                    //计数器减一,继续执行主线程
                    countDownLatch.countDown();
                }else {

                }
            });
            //阻塞当前线程(主线程)
            countDownLatch.await();
        } catch (Exception e) {
            log.info("zookeeper初始化失败");
        }
    }



    /**
     * @Author lipu
     * @Date 2021/5/20 17:34
     * @Description 关闭连接
     */
    public static void destroy(){
        try {
            zooKeeper.close();
            log.info("zookeeper连接关闭成功");
        } catch (Exception e) {
            log.info("zookeeper连接关闭失败");
            e.printStackTrace();
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/22 18:52
     * @Description 打开连接
     */
    public static void start(){

    }

    /**
     * @Author lipu
     * @Date 2021/5/22 18:54
     * @Description 获取会话的ID
     */
    public static long getSessionId(){
        return zooKeeper.getSessionId();
    }

    /**
     * @Author lipu
     * @Date 2021/5/20 17:37
     * @Description 同步创建持久化节点
     */
    public static void create(String path,String data,CreateMode createMode){
        try {
            //ZooDefs.Ids.OPEN_ACL_UNSAFE 开放ALC 所有人所有权限
            zooKeeper.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,createMode);
        } catch (Exception e) {
            log.info("创建zk节点失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 10:29
     * @Description 异步创建持久化节点
     */
    public static void createAsync(String path,String data,CreateMode createMode){

    }


    public static void main(String[] args) {
        ZkUtils.create("/node1/node2","小鸟程序员",CreateMode.PERSISTENT);
    }
}
