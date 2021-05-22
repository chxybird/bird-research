package com.bird;

import com.bird.listerner.ZkChildrenCache;
import com.bird.utils.CuratorUtils;
import org.apache.curator.framework.recipes.cache.*;

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
//        String data = CuratorUtils.getData("/node1");
//        System.out.println(data);
//        CuratorUtils.delete("/node1");
//        CuratorUtils.deleteWithChild("/node1");


//        CuratorUtils.watchNode("/node1", new NodeCacheListener() {
//            @Override
//            public void nodeChanged() throws Exception {
//                System.out.println("节点发生了变化");
//            }
//        });


        String data = CuratorUtils.getData("/node1");
        System.out.println(data);
        CuratorUtils.restart();
        String data2 = CuratorUtils.getData("/node1");
        System.out.println(data2);


//        PathChildrenCache pathChildrenCache=
//                new PathChildrenCache(CuratorUtils.getClient(),"/node1",true);
//        pathChildrenCache.getListenable().addListener(new ZkChildrenCache());
//        pathChildrenCache.start();
//        CuratorUtils.watchChild(pathChildrenCache);
//        TimeUnit.MINUTES.sleep(2);

    }

}
