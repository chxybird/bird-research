package com.bird.design.flyweight;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author lipu
 * @Date 2021/6/17 9:11
 * @Description 享元工厂类
 */
public class FlyweightFactory {
    //单例实例
    private static final FlyweightFactory FACTORY=new FlyweightFactory();
    //使用缓冲池 享元的核心 存放的是共享的内部状态
    private final Map<InnerStatus,InnerStatus> POOL=new HashMap<>();
    /**
     * @Author lipu
     * @Date 2021/6/17 9:16
     * @Description 创建享元对象
     */
    public Connection getBean(InnerStatus innerStatus){
        //缓冲池中有直接在缓冲池中获取
        if (POOL.containsKey(innerStatus)){
            innerStatus = POOL.get(innerStatus);
        }else {
            //缓存池中没有 放入缓冲池中
            POOL.put(innerStatus,innerStatus);
        }
        Connection connection=new Connection();
        connection.setInnerStatus(innerStatus);
        return connection;
    }

    /**
     * @Author lipu
     * @Date 2021/6/17 11:15
     * @Description 获取工厂类实例
     */
    public static FlyweightFactory getInstance(){
        return FACTORY;
    }
}
