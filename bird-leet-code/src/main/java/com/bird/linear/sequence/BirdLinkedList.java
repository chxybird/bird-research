package com.bird.linear.sequence;

import java.util.LinkedList;

/**
 * @Author lipu
 * @Date 2021/3/21 10:49
 * @Description 顺序表 链表 带头结点
 */
public class BirdLinkedList {

    //头结点
    private final Node head;
    //链表长度
    int length;

    /**
     * 元素节点 内部类
     */
    class Node{
        //元数据
        Object element;
        //下一个元素节点指针
        Node next;
        //上一个元素节点指针
        Node pre;
    }

    //构造器 初始化
    public BirdLinkedList() {
        head=new Node();
        length=0;
    }

    /**
     * @Author lipu
     * @Date 2021/3/26 13:22
     * @Description 添加操作
     */
}
