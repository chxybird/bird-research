package com.bird.linear.sequence;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author lipu
 * @Date 2021/3/13 22:28
 * @Description 顺序表 小鸟程序员
 */

public class BirdArray {

    //默认初始化大小
    private static final int DEFAULT_CAPACITY = 10;

    private static final int DEFAULT_LENGTH = 0;

    //扩容基准 2倍
    private static final int ARRAY_EXPEND = 2;

    //默认元数据
    private Object[] element = {};

    //当前数组长度
    private final int size;

    //当前有效元素个数 默认0 构造中初始化
    public int length;

    //空参构造 默认初始化容量为10
    public BirdArray() {
        element = new Object[DEFAULT_CAPACITY];
        this.size = DEFAULT_CAPACITY;
        this.length = DEFAULT_LENGTH;
    }

    //有参数构
    public BirdArray(int size) {
        //非法数据 默认初始化为10
        if (size <= 0) {
            element = new Object[DEFAULT_CAPACITY];
            this.size = DEFAULT_CAPACITY;
            this.length = DEFAULT_LENGTH;
        } else {
            //合法数据根据当前数据初始化数组容量
            element = new Object[size];
            this.size = size;
            this.length = DEFAULT_LENGTH;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/3/13 22:47
     * @Description 返回当前数组元素个数
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @Author lipu
     * @Date 2021/3/20 19:11
     * @Description 返回当前数组容量
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @Author lipu
     * @Date 2021/3/13 22:55
     * @Description 索引查询
     */
    public Object get(int index) throws Exception {
        //数组越界
        if (index < 0 || index > this.size) {
            throw new Exception("索引越界");
        }
        return element[index];
    }

    /**
     * @Author lipu
     * @Date 2021/3/13 23:53
     * @Description 根据索引替换元素
     */
    public void replace(int index, Object value) throws Exception {
        //非法索引插入
        if (index < 0 && index > this.length) {
            throw new Exception("索引越界");
        }
        this.element[index] = value;
    }

    /**
     * @Author lipu
     * @Date 2021/3/20 18:36
     * @Description 插入元素 元素后移
     */
    public void insert(int index, Object value) throws Exception {
        //非法索引插入判断
        if (index < 0 && index > this.length) {
            throw new Exception("索引越界");
        }
        //扩容判断
        if (IsExpend()) {
            expend();
        }
        //插入元素 元素后移
        int i;
        //1.元素后移
        for (i = this.length; i >index ; i--) {
            element[i]=element[i-1];
        }
        //2.插入元素
        element[i]=value;
        //3.有效元素个数+1
        this.length++;
    }

    /**
     * @Author lipu
     * @Date 2021/3/20 19:15
     * @Description 添加元素 尾插
     */
    public void add(Object value) {
        //扩容判断
        if (IsExpend()) {
            expend();
        }
        //尾插法 插入元素
        this.element[this.length] = value;
        this.length++;
    }

    /**
     * @Author lipu
     * @Date 2021/3/20 20:56
     * @Description 打印数组元素
     */
    public void printArray(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < this.length; i++) {
            stringBuilder.append(this.element[i]).append(",");
        }
        stringBuilder.append("]");
        //去除最后一个逗号
        stringBuilder.deleteCharAt(stringBuilder.length()-2);
        System.out.println(stringBuilder.toString());
    }
    
    /**
     * @Author lipu
     * @Date 2021/3/20 21:43 
     * @Description 删除元素 元素前移
     */
    public void delete(int index){
        //元素迁移 覆盖
        for (int i = index; i < this.length; i++) {
            this.element[i]=this.element[i+1];
        }
        this.length--;
    }




    /**
     * @Author lipu
     * @Date 2021/3/20 19:48
     * @Description 判断是否扩容
     */
    public boolean IsExpend() {
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
        for (int i = 0; i < this.element.length; i++) {
            array[i] = this.element[i];
        }
        this.element = array;
    }




    public static void main(String[] args) throws Exception {
        BirdArray array=new BirdArray(5);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(7);
        array.insert(0,20);
        array.delete(2);
        System.out.println(array.getLength());
        array.printArray();
    }
}
