package com.bird.linear.queue;

/**
 * @Author lipu
 * @Date 2021/04/06 13:40
 * @Description 双端队列 数组存储 支持扩容
 */
public class BirdArrayDeque {

    //默认初始化大小
    private static final int DEFAULT_CAPACITY = 10;

    private static final int DEFAULT_LENGTH = 0;

    //扩容基准 2倍
    private static final int ARRAY_EXPEND = 2;

    //默认元数据
    private Object[] queue = {};

    //当前数组长度
    private final int size;

    //当前有效元素个数 默认0 构造中初始化
    public int length;

    //空参构造
    public BirdArrayDeque() {
        queue = new Object[DEFAULT_CAPACITY];
        this.size = DEFAULT_CAPACITY;
        this.length = DEFAULT_LENGTH;
    }


}
