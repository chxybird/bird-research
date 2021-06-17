package com.bird.design.flyweight;

import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/6/16 11:59
 * @Description 具体的享元对象
 */
@Data
public class Connection implements Flyweight {
    //内部状态,可以在缓存中共享得到
    private InnerStatus innerStatus;
    //外部状态 每个对象的外部状态不共享
    private OuterStatus outerStatus;
    /**
     * @Author lipu
     * @Date 2021/6/17 9:50
     * @Description 外部状态
     */
    @Override
    public void operation(OuterStatus outerStatus) {
        this.outerStatus=outerStatus;
    }
}
