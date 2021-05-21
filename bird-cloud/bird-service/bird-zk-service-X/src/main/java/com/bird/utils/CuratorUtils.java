package com.bird.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/5/21 11:23
 * @Description zookeeper工具类
 */
@Slf4j
public class CuratorUtils {

    public static final String IP = "192.168.6.128:2181";
    private static CuratorFramework client;
    /**
     * 连接超时时间
     */
    private static final Integer CONNECTION_TIMEOUT=5000;
    /**
     * 心跳时间
     */
    private static final Integer SESSION_TIMEOUT=5000;

    //初始化
    static {
        client = CuratorFrameworkFactory.builder()
                .connectString(IP)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        //打开连接
        client.start();
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 15:53
     * @Description 关闭连接
     */
    public static void close(){
        client.close();
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 15:54
     * @Description 打开连接
     */
    public static void open(){
        client.start();
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 15:56
     * @Description 添加监听器
     */
    public static void addWatch(String path,CuratorCacheListener listener){
        //NodeCache(只监听当前节点) PathChildrenCache(监听当前节点的子节点) TreeCache(监听当前节点以及子节点)
        CuratorCache curatorCache=CuratorCache.builder(client,path).build();
        curatorCache.listenable().addListener(listener);
        curatorCache.start();
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 15:39
     * @Description 判断节点是否是持久化节点
     */
    public boolean isDurable(String path){
        if (path==null){
            log.info("节点路径不能为空,无法执行");
            return false;
        }
        try {
            //获取节点状态信息
            Stat stat = client.checkExists().forPath(path);
            if (stat==null){
                log.info("节点不存在!!");
                return false;
            }
            return stat.getEphemeralOwner() > 0;
        } catch (Exception e) {
            log.error("节点是否为持久化节点判断异常");
            return false;
        }
    }


    /**
     * @Author lipu
     * @Date 2021/5/21 15:34
     * @Description 获取当前会话ID
     */
    public void getSessionId(){

    }


    /**
     * @Author lipu
     * @Date 2021/5/21 11:29
     * @Description 创建节点
     */
    public static void create(String path, String data, CreateMode createMode) {
        if (path==null||createMode==null){
            log.info("节点路径不能为空、节点类型也不能为空,无法创建");
            return;
        }
        try {
            client.create()
                    .withMode(createMode)
                    .forPath(path, data.getBytes());
        } catch (Exception e) {
            log.error("创建节点失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 13:59
     * @Description 递归创建节点
     */
    public static void createWithParent(String path, String data, CreateMode createMode){
        if (path==null||createMode==null){
            log.info("节点路径不能为空、节点类型也不能为空,无法创建");
            return;
        }
        try {
            client.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(createMode)
                    .forPath(path, data.getBytes());
        } catch (Exception e) {
            log.error("创建节点失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 14:08
     * @Description 查询当前节点下的子节点
     */
    public static List<String> getChildList(String path){
        if (path==null){
            log.info("节点路径不能为空,无法执行");
            return null;
        }
        try {
            return client.getChildren().forPath(path);
        } catch (Exception e) {
            log.error("获取节点失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 14:17
     * @Description 判断节点是否存在
     */
    public static boolean exists(String path){
        if (path==null){
            log.info("节点路径不能为空,无法执行");
            return false;
        }
        try {
            Stat stat = client.checkExists().forPath(path);
            if (stat==null){
                return false;
            }else {
                return true;
            }
        } catch (Exception e) {
            log.error("判断节点是否存在失败");
            return false;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 14:26
     * @Description 获取节点状态信息
     */
    public static Stat getStat(String path){
        if (path==null){
            log.info("节点路径不能为空,无法执行");
            return null;
        }
        try {
            return client.checkExists().forPath(path);
        } catch (Exception e) {
            log.error("节点状态信息获取失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 14:32
     * @Description 获取节点数据
     */
    public static String getData(String path){
        if (path==null){
            log.info("节点路径不能为空,无法执行");
            return null;
        }
        try {
            byte[] bytes = client.getData().forPath(path);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("获取节点信息失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 14:16
     * @Description 删除节点
     */
    public static void delete(String path){
        if (path==null){
            log.info("节点路径不能为空,无法执行");
            return;
        }
        try {
            List<String> stringList = client.getChildren().forPath(path);
            if (stringList.size()>0){
                log.info("该节点存在子节点,不允许删除");
                return;
            }
            client.delete().forPath(path);
        } catch (Exception e) {
            log.error("节点删除失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 14:56
     * @Description 删除节点(包含其子节点)
     */
    public static void deleteWithChild(String path){
        if (path==null){
            log.info("节点路径不能为空,无法执行");
            return;
        }
        try {
            client.delete().deletingChildrenIfNeeded().forPath(path);
        } catch (Exception e) {
            log.error("节点删除失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/21 15:21
     * @Description 更新数据
     */
    public static void update(String path,String data){
        if (path==null){
            log.info("节点路径不能为空,无法执行");
            return;
        }
        try {
            client.setData().forPath(path,data.getBytes());
        } catch (Exception e) {
            log.error("节点更新失败");
        }
    }




}
