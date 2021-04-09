package com.bird.linear.queue;

/**
 * @Author lipu
 * @Date 2021/04/06 13:40
 * @Description 双端队列 数组存储 支持扩容
 */
public class BirdArrayDeque {

    //默认初始化大小
    private static final int DEFAULT_CAPACITY = 5;

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

    /**
     * @Author lipu
     * @Date 2021/4/9 16:06
     * @Description 返回当前队列元素个数
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @Author lipu
     * @Date 2021/4/9 16:06
     * @Description 入队列头插法
     */
    public void putHead(Object data) {
        //队列扩容判断
        if (isExpend()) {
            expend();
        }
        //队列是否为空 为空直接设置 不为空元素后移
        if (this.length == 0) {
            this.queue[0]=data;
        }else {
            //元素后移 头插
            int i=this.length;
            for (; i > 0; i--) {
                queue[i]=queue[i-1];
            }
            queue[i]=data;
        }
        this.length++;
    }

    /**
     * @Author lipu
     * @Date 2021/4/9 16:07
     * @Description 入队列尾插
     */
    public void putTail(Object data) {
        //队列扩容判断
        if (isExpend()) {
            expend();
        }
        //直接在最后一个元素后面插入
        this.queue[length++]=data;
    }

    /**
     * @Author lipu
     * @Date 2021/4/9 16:09
     * @Description 出队列 头出法
     */
    public void deleteHead() {
        //非空判断
        if (this.length>0){
            //元素前移动覆盖
            for (int i = 1; i < this.length; i++) {
                this.queue[i-1]=this.queue[i];
            }
            this.length--;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/9 16:10
     * @Description 出队列 尾出法
     */
    public void deleteTail() {
        //直接有效长度-1即可 做非空判断
        if (this.length>0){
            this.length--;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/3/20 19:48
     * @Description 判断是否扩容
     */
    public boolean isExpend() {
        return this.length + 1 > this.size;
    }

    /**
     * @Author lipu
     * @Date 2021/3/20 19:54
     * @Description 数组扩容
     */
    public void expend() {
        //数组扩容 并将元素拷贝到新的数组中
        Object[] array = new Object[this.size * ARRAY_EXPEND];
        for (int i = 0; i < this.queue.length; i++) {
            array[i] = this.queue[i];
        }
        this.queue = array;
    }

    /**
     * @Author lipu
     * @Date 2021/3/20 20:56
     * @Description 打印数组元素
     */
    public void printArrayDeque(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < this.length; i++) {
            stringBuilder.append(this.queue[i]).append(",");
        }
        stringBuilder.append("]");
        if (stringBuilder.toString().contains(",")) {
            //去除最后一个逗号
            stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        }
        System.out.println(stringBuilder.toString());
    }


    public static void main(String[] args) {
        BirdArrayDeque deque=new BirdArrayDeque();
        deque.putHead(1);
        deque.putHead(2);
        deque.putHead(3);
        deque.putHead(4);
        deque.putHead(5);
        deque.putHead(6);
        deque.putTail(7);
        deque.printArrayDeque();
        deque.deleteHead();
        deque.deleteTail();
        deque.printArrayDeque();
    }
}
