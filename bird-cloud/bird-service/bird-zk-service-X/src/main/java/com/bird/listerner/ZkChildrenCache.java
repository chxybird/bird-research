package com.bird.listerner;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;

/**
 * @Author lipu
 * @Date 2021/5/22 22:33
 * @Description
 */
public class ZkChildrenCache implements PathChildrenCacheListener {
    /**
     * @Author lipu
     * @Date 2021/5/22 22:35
     * @Description
     */
    @Override
    public void childEvent(CuratorFramework curatorFramework,
                           PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
        ChildData childData = pathChildrenCacheEvent.getData();
        if (childData!=null){
            //节点路径
            String path = childData.getPath();
            System.out.println(path);
            //节点内容
            byte[] bytes = childData.getData();
            System.out.println(new String(bytes));
            //节点状态
            Stat stat = childData.getStat();
            System.out.println(stat);
        }
    }
}
