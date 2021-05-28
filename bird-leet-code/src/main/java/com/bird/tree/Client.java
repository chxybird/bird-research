package com.bird.tree;

/**
 * @Author lipu
 * @Date 2021/5/7 17:11
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        BirdBinaryTree tree=new BirdBinaryTree();
        tree.add(20);
        tree.add(10);
        tree.add(30);
        tree.add(15);
        System.out.println("添加完毕");
        tree.delete(20);
        tree.in();
    }

}
