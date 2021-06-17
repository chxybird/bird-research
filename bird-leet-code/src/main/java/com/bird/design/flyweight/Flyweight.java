package com.bird.design.flyweight;

/**
 * @Author lipu
 * @Date 2021/6/16 11:55
 * @Description 抽象享元对象
 */
public interface Flyweight {

    /**
     * @Author lipu
     * @Date 2021/6/17 10:53
     * @Description 提供外部状态操作
     */
    void operation(OuterStatus outerStatus);

}
