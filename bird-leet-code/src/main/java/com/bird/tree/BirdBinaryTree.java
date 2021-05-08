package com.bird.tree;


/**
 * @Author lipu
 * @Date 2021/5/7 15:44
 * @Description 二叉树 二叉排序树
 */
public class BirdBinaryTree {
    /**
     * 树根节点
     */
    private final Node ROOT = new Node();

    static class Node {
        //元数据
        Integer data;
        //左节点
        Node left;
        //又节点
        Node right;
        //父节点
        Node father;
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 15:57
     * @Description 二叉排序树添加
     */
    public void add(Integer data) {
        //如果根节点为空直接插入
        if (ROOT.data == null) {
            ROOT.data = data;
            return;
        }
        // 从根节点递归搜索 寻找要插入的位置
        Node node = find(ROOT, data);
        //插入元素
        Node e=new Node();
        e.data=data;
        if (data>node.data){
            node.right=e;
            e.father=node;
        }else {
            node.left=e;
            e.father=node;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 16:36
     * @Description 递归查找插入的位置
     */
    private Node find(Node node, Integer data) {
//        while (){
//
//        }
//        //递归寻找插入的位置
//        if (node.data>data){
//            //左递归
//            find(node.left,data);
//        }else {
//            //右递归
//            find(node.right,data);
//        }
        return null;
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 17:18
     * @Description 前序遍历 根 左 右
     */
    public void pre(Node node){
        if (node!=null){
            //访问根节点
            System.out.print(node.data);
            //递归遍历左子树
            pre(node.left);
            //递归遍历右子树
            pre(node.right);
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 17:48
     * @Description 先序遍历 根 左 右
     */
    public void preOrder(){
        //从根节点开始遍历
        pre(this.ROOT);
    }

    /**
     * @Author lipu
     * @Date 2021/5/7 17:13
     * @Description
     */
//    public void preSearch(){
//        StringBuilder stringBuilder=new StringBuilder();
//        stringBuilder.append("[");
////        for (int i = 0; i < this.length; i++) {
////            stringBuilder.append(this.queue[i]).append(",");
////        }
//        stringBuilder.append("]");
//        if (stringBuilder.toString().contains(",")) {
//            //去除最后一个逗号
//            stringBuilder.deleteCharAt(stringBuilder.length() - 2);
//        }
//        System.out.println(stringBuilder.toString());
//    }

    /**
     * @Author lipu
     * @Date 2021/5/7 17:13
     * @Description 中序遍历
     */

    /**
     * @Author lipu
     * @Date 2021/5/7 17:13
     * @Description 后续遍历
     */

}
