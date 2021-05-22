package com.bird.listerner;

import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;

import java.nio.charset.StandardCharsets;

/**
 * @Author lipu
 * @Date 2021/5/22 21:49
 * @Description
 */
public class ZkNodeCache implements NodeCacheListener {
    private NodeCache nodeCache;
    /**
     * @Author lipu
     * @Date 2021/5/22 21:50
     * @Description zookeeper节点变化时的逻辑
     */
    @Override
    public void nodeChanged() throws Exception {
        byte[] data = nodeCache.getCurrentData().getData();
        System.out.println(new String(data, StandardCharsets.UTF_8));
    }
}
