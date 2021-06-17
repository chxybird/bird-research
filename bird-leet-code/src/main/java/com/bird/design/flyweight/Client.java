package com.bird.design.flyweight;

import java.util.UUID;

/**
 * @Author lipu
 * @Date 2021/6/17 11:14
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        FlyweightFactory factory=FlyweightFactory.getInstance();
        //设置连接信息
        InnerStatus innerStatus=new InnerStatus();
        innerStatus.setDriver("com.bird");
        innerStatus.setUsername("bird");
        innerStatus.setPassword("bird");
        innerStatus.setUrl("bird://127.0.0.1:1515");

        //创建两个连接 内部状态共享 外部状态不共享
        Connection connOne = factory.getBean(innerStatus);
        connOne.operation(new OuterStatus("conn-one", UUID.randomUUID().toString().replace("-","")));
        Connection connTwo = factory.getBean(innerStatus);
        connTwo.operation(new OuterStatus("conn-two", UUID.randomUUID().toString().replace("-","")));
        System.out.println(connOne.getInnerStatus()==connTwo.getInnerStatus());
        System.out.println(connOne);
        System.out.println(connTwo);

    }
}
