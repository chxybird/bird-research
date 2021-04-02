package com.bird.linear.stack;

/**
 * @Author lipu
 * @Date 2021/4/2 15:57
 * @Description 栈 顺序表实现 支持扩容
 */
public class BirdArrayStack {

    //默认初始化大小
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 当前元素个数以及总长度初始化大小
     */
    private static final int DEFAULT_LENGTH = 0;

    private static final int DEFAULT_CURSOR = -1;

    //扩容基准 2倍
    private static final int ARRAY_EXPEND = 2;

    //默认元数据
    private Object[] element = {};

    //当前栈长度
    private final int size;

    //当前有效元素个数 默认0 构造中初始化
    private int length;
    /**
     * 栈指针
     */
    private int p;

    //空参构造默认初始化大小
    public BirdArrayStack() {
        element = new Object[DEFAULT_CAPACITY];
        this.size = DEFAULT_CAPACITY;
        this.length = DEFAULT_LENGTH;
        this.p = DEFAULT_CURSOR;
    }

    //有参数构
    public BirdArrayStack(int size) {
        //非法数据 默认初始化为10
        if (size <= 0) {
            element = new Object[DEFAULT_CAPACITY];
            this.size = DEFAULT_CAPACITY;
            this.length = DEFAULT_LENGTH;
            this.p = DEFAULT_CURSOR;
        } else {
            //合法数据根据当前数据初始化数组容量
            element = new Object[size];
            this.size = size;
            this.length = DEFAULT_LENGTH;
            this.p = DEFAULT_CURSOR;
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
        for (int i = 0; i < this.element.length; i++) {
            array[i] = this.element[i];
        }
        this.element = array;
    }

    /**
     * @Author lipu
     * @Date 2021/4/2 16:20
     * @Description 进栈操作
     */
    public void push(Object data) {
        //扩容判断
        if (isExpend()) {
            expend();
        }
        this.element[++p] = data;
        this.length++;
    }

    /**
     * @Author lipu
     * @Date 2021/4/2 16:20
     * @Description 出栈操作
     */
    public void pop() {
        //栈不为空时出栈操作
        if (this.length>0){
            p--;
            this.length--;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/2 16:22
     * @Description 打印数组栈
     */
    public void printArrayStack() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < this.length; i++) {
            stringBuilder.append(this.element[i]).append(",");
        }
        stringBuilder.append("]");
        if (stringBuilder.toString().contains(",")) {
            //去除最后一个逗号
            stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        }
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) {
        BirdArrayStack stack=new BirdArrayStack();
        stack.push("张三");
        stack.push("李四");
        stack.push("王五");
        stack.push("赵六");
        stack.push("张三");
        stack.push("李四");
        stack.push("王五");
        stack.push("赵六");
        stack.push("张三");
        stack.push("李四");
        stack.push("王五");
        stack.push("赵六");
        stack.printArrayStack();
    }

}
