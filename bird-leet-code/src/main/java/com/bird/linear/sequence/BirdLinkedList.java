package com.bird.linear.sequence;

/**
 * @Author lipu
 * @Date 2021/3/21 10:49
 * @Description 顺序表 链表 带头结点 双向链表
 */
public class BirdLinkedList {

    //头结点
    private final Node NODE_HEAD = new Node();
    //链表长度
    int length = 0;

    /**
     * 元素节点 内部类
     */
    static class Node {
        //元数据
        Object data;
        //下一个元素节点指针
        Node next;
        //上一个元素节点指针
        Node pre;
    }

    //构造器 初始化
    public BirdLinkedList() {

    }

    /**
     * @Author lipu
     * @Date 2021/3/26 13:22
     * @Description 添加操作 尾插法
     */
    public void addLast(Object data) {
        //当前指针位置
        Node p = NODE_HEAD;
        //创建节点
        Node node = new Node();
        node.data=data;
        //当插入的是第一个节点时
        if (this.length == 0) {
            NODE_HEAD.next = node;
            node.pre = NODE_HEAD;
            this.length++;
        } else {
            //当插入的节点不为第一个节点
            while (p.next != null) {
                p = p.next;
            }
            p.next = node;
            node.pre = p;
            this.length++;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/3/28 18:46
     * @Description 添加操作 头插法
     */
    public void addFirst(Object data) {
        //创建节点
        Node node = new Node();
        node.data=data;
        //当有节点的时候
        if (this.length > 0) {
            node.next = NODE_HEAD.next;
            NODE_HEAD.next.pre = node;
            NODE_HEAD.next = node;
            node.pre = NODE_HEAD;
            this.length++;
        } else {
            //当没有节点的时候 直接插入到头结点之后
            NODE_HEAD.next = node;
            node.pre = NODE_HEAD;
            this.length++;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/3/28 19:16
     * @Description 按索引查找 1为开始
     */
    public Object findByIndex(int index) throws Exception {
        //定义指针
        Node pointer = NODE_HEAD;
        //越界判断
        if (this.length < index) {
            throw new Exception("索引越界");
        }
        //移动指针返回值
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.data;
    }


    /**
     * @Author lipu
     * @Date 2021/3/28 19:16
     * @Description 按值查找 返回逻辑值
     */
    public boolean findByValue(Object value) {
        //定义指针
        Node pointer = NODE_HEAD;
        while (pointer.next != null) {
            //值匹配
            if (value.equals(pointer.next.data)) {
                return true;
            }
            //移动指针
            pointer = pointer.next;
        }
        return false;
    }

    /**
     * @Author lipu
     * @Date 2021/4/2 9:57
     * @Description 删除节点(根据索引)
     */
    public void removeByIndex(int index) throws Exception {
        //索引越界判断
        if (index <= 0 || index > this.length) {
            throw new Exception("索引越界");
        }
        //定义头指针
        Node pointer = NODE_HEAD;
        //寻找索引节点的前驱结点
        for (int i = 0; i < index - 1; i++) {
            pointer = pointer.next;
        }
        //断链操作
        pointer.next.pre = null;
        pointer.next = pointer.next.next;
        //防止被删除的节点为最后一个节点
        if (pointer.next != null) {
            pointer.next.pre.next = null;
            pointer.next.pre = null;
        }
        this.length--;
    }

    /**
     * @Author lipu
     * @Date 2021/4/2 10:28
     * @Description 获取链表长度
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @Author lipu
     * @Date 2021/4/2 11:55
     * @Description 打印数组元素
     */
    public void printList() {
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
        //去除最后一个逗号
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) throws Exception {
        BirdLinkedList list=new BirdLinkedList();
        list.addLast("张三");
        list.addLast("李四");
        list.addLast("王五");
        list.addLast("赵六");
        list.addFirst("老大");
        list.printList();
        Object value = list.findByIndex(3);
        System.out.println(value);
        System.out.println(list.findByValue("赵六"));
        System.out.println(list.getLength());
        list.removeByIndex(5);
        list.printList();
    }
}
