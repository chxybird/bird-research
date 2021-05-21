package com.bird;

import com.bird.utils.CuratorUtils;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.CuratorCacheListenerBuilder;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author lipu
 * @Date 2021/5/21 11:34
 * @Description
 */
public class Client {
    public static void main(String[] args) throws Exception {
//        CuratorUtils.create("/node1/node2","小鸟程序员", CreateMode.PERSISTENT);
//        CuratorUtils.createWithParent("/node1/node5/node9","mybatis",CreateMode.PERSISTENT);
//        List<String> childList = CuratorUtils.getChildList("/");
//        for (String child:childList) {
//            System.out.println(child);
//        }
//        CuratorUtils.exists("/node1/x");
//        System.out.println(CuratorUtils.getStat("/node1"));
//        Stat stat = CuratorUtils.getStat("/node1");
        String data = CuratorUtils.getData("/node1");
        System.out.println(data);
//        CuratorUtils.delete("/node1");
//        CuratorUtils.deleteWithChild("/node1");





//        CuratorCacheListener listener = CuratorCacheListener.builder()
//                .forNodeCache(() -> System.out.println("节点发生了变化")).build();
//        CuratorUtils.addWatch("/node1", listener);
//        TimeUnit.MINUTES.sleep(2);

    }

}
