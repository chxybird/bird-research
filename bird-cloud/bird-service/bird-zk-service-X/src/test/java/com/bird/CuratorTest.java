package com.bird;

import com.bird.utils.CuratorUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author lipu
 * @Date 2021/5/23 12:57
 * @Description
 */
@SpringBootTest(classes = ZkServiceXApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CuratorTest {

    /**
     * @Author lipu
     * @Date 2021/5/23 12:59
     * @Description Curator事务
     */
    @Test
    public void test1() throws Exception {
        CuratorFramework client = CuratorUtils.getClient();
        //开启事务
        client.inTransaction()
                //创建节点 设定可以创建成功
                .create().forPath("/node3")
                //修改节点的值 没有此节点 所以无法修改
                .and().setData().forPath("/node3/node4")
                //提交事务
                .and().commit();
    }

    /**
     * @Author lipu
     * @Date 2021/5/23 15:17
     * @Description Curator分布式锁
     */
    @Test
    public void test2() throws Exception {
        //模拟两个客户端获取一把锁
        //线程一 尝试获取锁
        Thread t1=new Thread(() -> {
            try{
                CuratorFramework client = CuratorFrameworkFactory.builder()
                        .connectString("192.168.6.128:2181")
                        .connectionTimeoutMs(5000)
                        .sessionTimeoutMs(5000)
                        //重试策略 直接忽略理解 就这样设置
                        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                        .build();
                //打开连接
                client.start();
                //创建分布式排他锁 参数一 连接对象 参数二 节点路径
                InterProcessLock lock=new InterProcessMutex(client,"/lock");
                //获取锁
                lock.acquire();
                System.out.println("客户端一获取了锁");
                TimeUnit.SECONDS.sleep(20);
                //释放锁
                lock.release();
                System.out.println("客户端一释放了锁");
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Thread t2=new Thread(() -> {
            try{
                CuratorFramework client = CuratorFrameworkFactory.builder()
                        .connectString("192.168.6.128:2181")
                        .connectionTimeoutMs(5000)
                        .sessionTimeoutMs(5000)
                        //重试策略 直接忽略理解 就这样设置
                        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                        .build();
                //打开连接
                client.start();
                //创建分布式排他锁 参数一 连接对象 参数二 节点路径
                InterProcessLock lock=new InterProcessMutex(client,"/lock");
                //获取锁
                lock.acquire();
                System.out.println("客户端二获取了锁");
                TimeUnit.SECONDS.sleep(20);
                //释放锁
                lock.release();
                System.out.println("客户端二释放了锁");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(60);
        System.out.println("主线程执行结束");
    }

    /**
     * @Author lipu
     * @Date 2021/5/23 16:45
     * @Description 同一客户端也可以实现分布式锁
     */
    @Test
    public void test3() throws InterruptedException {
        Thread t1=new Thread(()->{
            try {
                InterProcessMutex lock = CuratorUtils.lock("/lock");
                System.out.println("客户端一获取了锁");
                TimeUnit.SECONDS.sleep(20);
                CuratorUtils.unLock(lock);
                System.out.println("客户端一释放了锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2=new Thread(()->{
            try {
                InterProcessMutex lock = CuratorUtils.lock("/lock");
                System.out.println("客户端二获取了锁");
                TimeUnit.SECONDS.sleep(20);
                CuratorUtils.unLock(lock);
                System.out.println("客户端二释放了锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("主线程执行完毕");
    }

}
