package com.bird.linear.stack;

import com.bird.linear.sequence.BirdLinkedList;

/**
 * @Author lipu
 * @Date 2021/4/2 15:58
 * @Description 栈 链表实现
 */
public class BirdLinkedStack {

    /** 头指针 */
    private final Node NODE_HEAD;
    /** 头指针 */
    private Node top;
    /** 栈的长度 */
    private int length;

    /**
     * 元素节点 内部类
     */
    static class Node {
        //元数据
        Object data;
        //下一个元素指针
        Node next;
    }

    //空参构造方法
    public BirdLinkedStack() {
        this.NODE_HEAD=new Node();
        //初始化 空链栈指向头节点
        this.top=NODE_HEAD;
        this.length=0;
    }

    /**
     * @Author lipu
     * @Date 2021/4/6 9:15
     * @Description 入栈
     */
    public void push(Object data){
        //创建节点
        Node node=new Node();
        node.data=data;
        //头插法接入链表 空栈判断
        if (NODE_HEAD.next!=null){
            node.next=NODE_HEAD.next;
        }
        NODE_HEAD.next=node;
        //栈顶指针更新到栈顶节点
        this.top=node;
        //栈长度+1
        this.length++;
    }

    /**
     * @Author lipu
     * @Date 2021/4/6 11:27
     * @Description 出栈
     */
    public void pop(){
        //空栈校验 或者this.length>0
        if (NODE_HEAD.next!=null){
            //删除头节点 出栈操作
            NODE_HEAD.next=NODE_HEAD.next.next;
            //更新栈顶指针
            this.top=NODE_HEAD.next;
            //栈长度-1
            this.length--;
        }
    }


    /**
     * @Author lipu
     * @Date 2021/4/6 11:43
     * @Description 打印栈指针
     */
    public void printLinkedStack() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        //定义头指针
        Node pointer = NODE_HEAD;
        for (int i = 0; i < this.length; i++) {
            String element = pointer.next.data.toString();
            stringBuilder.append(element).append(",");
            pointer = pointer.next;
        }
        stringBuilder.append("]");
        if (stringBuilder.toString().contains(",")) {
            //去除最后一个逗号
            stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        }
        System.out.println(stringBuilder.toString());
    }



    public static void main(String[] args) {
        BirdLinkedStack stack=new BirdLinkedStack();
        stack.push("张三");
        stack.push("李四");
        stack.push("王五");
        stack.push("赵六");
        stack.pop();
        stack.printLinkedStack();
    }

}
